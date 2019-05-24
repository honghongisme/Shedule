package com.example.administrator.shedule.course;

public class GridConfig {

    // 最左边表示节数的一列权重
    private float leftHeaderWeight;
    // 节数列左右margin
    private int leftHeaderLeftMargin;
    private int leftHeaderRightMargin;
    // 课程格子权重
    private float singleCourseWeight;
    // 课程格子左右margin
    private int singleCourseLeftMargin;
    private int singleCourseRightMargin;
    // 课程格子padding
    private int singleCourseVerticalPadding;
    private int singleCourseHorizontalPadding;

    public GridConfig(float leftHeaderWeight, int leftHeaderLeftMargin, int leftHeaderRightMargin, float singleCourseWeight, int singleCourseLeftMargin, int singleCourseRightMargin, int singleCourseVerticalPadding, int singleCourseHorizontalPadding) {
        this.leftHeaderWeight = leftHeaderWeight;
        this.leftHeaderLeftMargin = leftHeaderLeftMargin;
        this.leftHeaderRightMargin = leftHeaderRightMargin;
        this.singleCourseWeight = singleCourseWeight;
        this.singleCourseLeftMargin = singleCourseLeftMargin;
        this.singleCourseRightMargin = singleCourseRightMargin;
        this.singleCourseVerticalPadding = singleCourseVerticalPadding;
        this.singleCourseHorizontalPadding = singleCourseHorizontalPadding;
    }

    public float getLeftHeaderWeight() {
        return leftHeaderWeight;
    }

    public int getLeftHeaderLeftMargin() {
        return leftHeaderLeftMargin;
    }

    public int getLeftHeaderRightMargin() {
        return leftHeaderRightMargin;
    }

    public float getSingleCourseWeight() {
        return singleCourseWeight;
    }

    public int getSingleCourseLeftMargin() {
        return singleCourseLeftMargin;
    }

    public int getSingleCourseRightMargin() {
        return singleCourseRightMargin;
    }

    public int getSingleCourseVerticalPadding() {
        return singleCourseVerticalPadding;
    }

    public int getSingleCourseHorizontalPadding() {
        return singleCourseHorizontalPadding;
    }
}
