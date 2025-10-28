package com.project.havebin;

import java.io.*;
import java.util.*;

import com.project.havebin.trashcan.adapter.out.persistence.entity.TrashCanJpaEntity;
import com.project.havebin.trashcan.adapter.out.persistence.repository.TrashCanCustomRepositoryImpl;
import com.project.havebin.trashcan.domain.enums.Categories;
import com.project.havebin.trashcan.domain.enums.State;
import com.project.havebin.user.adapter.out.persistence.entity.UserJpaEntity;
import com.project.havebin.user.adapter.out.persistence.repository.UserCustomRepositoryImpl;
import com.project.havebin.user.domain.enums.RoleType;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static io.jsonwebtoken.lang.Strings.clean;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestCaseInput implements CommandLineRunner {
    private final UserCustomRepositoryImpl userRepository;
    private final TrashCanCustomRepositoryImpl trashCanRepository;

    @Override
    public void run(String... args) {
        userCaseInput();
        trashCanCaseInput();
    }

    public void userCaseInput() {
        InputStream is = getClass().getResourceAsStream("/user.csv");
        InputStreamReader isr = new InputStreamReader(is);

        try (CSVReader reader = new CSVReader(isr)) {
            List<String[]> records = reader.readAll();

            for (int i = 1; i < records.size(); i++) {
                String[] row = records.get(i);

                Long id = Long.valueOf(row[0]);
                String email = clean(row[2]);
                String nickname = clean(row[3]);
                String password = clean(row[4]);
                String profileImg = clean(row[5]);
                RoleType roleType = RoleType.valueOf(clean(row[6]).toUpperCase());

                UserJpaEntity user = UserJpaEntity.builder()
                        .email(email)
                        .nickname(nickname)
                        .password(password)
                        .profileImagePath(profileImg)
                        .roleType(roleType)
                        .build();

                user.setPK(id);

                UserJpaEntity userTemp = userRepository.save(user);
            }

            List<UserJpaEntity> users = userRepository.findAll();
            log.info("✅ CSV에서 유저 데이터 로드 완료!");
            log.info("총 유저 수: {}", users.size());
        } catch (Exception e) {
            log.error("❌ CSV 로드 중 오류 발생: {}", e.getMessage());
        }
    }

    public void trashCanCaseInput() {
        InputStream is = getClass().getResourceAsStream("/trashcan.csv");
        InputStreamReader isr = new InputStreamReader(is);

        try (CSVReader reader = new CSVReader(isr)) {
            List<String[]> records = reader.readAll();

            for (int i = 1; i < records.size(); i++) {
                String[] row = records.get(i);

                double latitude = Double.parseDouble(row[0]);
                double longitude = Double.parseDouble(row[1]);
                //Long id = Long.valueOf(row[2]);
                UserJpaEntity user = userRepository.findById(Long.valueOf(row[3])).get();
                String address = row[4];
                String category = row[5];
                String date = row[6];
                String detailAddress = row[7];
                String roadviewImgPath = row[8];
                String state = row[9];

                TrashCanJpaEntity trashCan = TrashCanJpaEntity.builder()
                                .latitude(latitude)
                                .longitude(longitude)
                                .roadviewImgpath(roadviewImgPath)
                                .findUser(user)
                                .date(date)
                                .address(address)
                                .detailAddress(detailAddress)
                                .categories(Categories.valueOf(category.toUpperCase()))
                                .state(State.valueOf(state.toUpperCase()))
                                .build();

                Long id = TSID.Factory.getTsid().toLong();
                trashCan.setPK(id);

                //log.info("이름={}, 주소={}. 위도={}, 경도={}", user.getNickname(), address, latitude, longitude);
                trashCanRepository.save(trashCan);
            }

            List<TrashCanJpaEntity> temps = trashCanRepository.findAll();
            log.info("✅ 쓰레기통 데이터 로드 완료!");
            log.info("총 쓰레기통 수: {}", temps.size());
        } catch (Exception e) {
            log.error("❌ trashcan.csv 로드 중 오류 발생: {}", e.getMessage());
        }
    }
}
