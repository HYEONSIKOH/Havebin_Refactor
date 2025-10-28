package com.project.havebin.trashcan.application.in;

import com.project.havebin.trashcan.adapter.in.web.response.GetAllTrashCanPositionResDto;

import java.util.List;

public interface TrashCanUseCase {
    List<GetAllTrashCanPositionResDto> getAllTrashCans();

}
