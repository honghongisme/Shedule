package com.example.administrator.shedule.course;

public class GridConfig {

    // 节数列左右margin
    private int leftHeaderLeftRightMargin;
    // 课程格子左右margin
    private int singleCourseLeftRightMargin;
    // 课程格子上下margin
    private int singleCourseTopBottomMargin;
    //
    private int singleCourseLeftRightPadding;
    private int singleCourseTopBottomPadding;

    public int getLeftHeaderLeftRightMargin() {
        return leftHeaderLeftRightMargin;
    }

    public GridConfig setLeftHeaderLeftRightMargin(int leftHeaderLeftRightMargin) {
        this.leftHeaderLeftRightMargin = leftHeaderLeftRightMargin;
        return this;
    }

    public int getSingleCourseLeftRightMargin() {
        return singleCourseLeftRightMargin;
    }

    public GridConfig setSingleCourseLeftRightMargin(int singleCourseLeftRightMargin) {
        this.singleCourseLeftRightMargin = singleCourseLeftRightMargin;
        return this;
    }

    public int getSingleCourseTopBottomMargin() {
        return singleCourseTopBottomMargin;
    }

    public GridConfig setSingleCourseTopBottomMargin(int singleCourseTopBottomMargin) {
        this.singleCourseTopBottomMargin = singleCourseTopBottomMargin;
        return this;
    }

    public int getSingleCourseLeftRightPadding() {
        return singleCourseLeftRightPadding;
    }

    public GridConfig setSingleCourseLeftRightPadding(int singleCourseLeftRightPadding) {
        this.singleCourseLeftRightPadding = singleCourseLeftRightPadding;
        return this;
    }

    public int getSingleCourseTopBottomPadding() {
        return singleCourseTopBottomPadding;
    }

    public GridConfig setSingleCourseTopBottomPadding(int singleCourseTopBottomPadding) {
        this.singleCourseTopBottomPadding = singleCourseTopBottomPadding;
        return this;
    }
}
