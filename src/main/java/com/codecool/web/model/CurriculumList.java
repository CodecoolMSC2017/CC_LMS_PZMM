package com.codecool.web.model;

import java.util.ArrayList;
import java.util.List;

public class CurriculumList {
    private List<Curriculum> curriculums;

    private CurriculumList() {
        curriculums = new ArrayList<>();
    }

    private static CurriculumList curriculumList = new CurriculumList();

    public static CurriculumList getInstance() {
        return curriculumList;
    }

    public List<Curriculum> getCurriculums() {
        return curriculums;
    }
}
