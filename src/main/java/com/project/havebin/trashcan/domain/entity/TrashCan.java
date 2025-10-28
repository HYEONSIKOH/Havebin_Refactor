package com.project.havebin.trashcan.domain.entity;

import com.project.havebin.trashcan.domain.enums.*;
import com.project.havebin.trashcan.domain.vo.*;
import com.project.havebin.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TrashCan {
    private final TrashCanNo id;
    private Location location;
    private RoadviewImagePath roadviewImagePath;
    private final User findUser;
    private Address address;
    private Categories categories;
    private State state;
    private FindDate findDate;

    @Builder
    public TrashCan(TrashCanNo id, Location location, RoadviewImagePath roadviewImagePath,
                    User findUser, Address address, Categories categories,
                    State state, FindDate findDate) {
        this.id = id;
        this.location = location;
        this.roadviewImagePath = roadviewImagePath;
        this.findUser = findUser;
        this.address = address;
        this.categories = categories;
        this.state = state;
        this.findDate = findDate;
    }
}
