package com.project.havebin.trashcan.application.in;

import com.project.havebin.trashcan.adapter.in.web.response.AllTrashCanPositionResDto;
import com.project.havebin.trashcan.adapter.in.web.response.TrashCanInfoResDto;

import java.util.List;

public interface TrashCanUseCase {
    List<AllTrashCanPositionResDto> getAllTrashCans();
    TrashCanInfoResDto getTrashCanInfoById(Long id);
}
