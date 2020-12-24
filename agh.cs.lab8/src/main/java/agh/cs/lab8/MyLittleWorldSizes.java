package agh.cs.lab8;

public class MyLittleWorldSizes {


    public int frameWidth;
    public int frameHeight;

    public int playgroundWidth;
    public int playgroundHeight;
    public int playgroundX;
    public int playgroundY;


    public int statisticsWidth;
    public int statisticsHeight;
    public int statisticsX;
    public int statisticsY;

    public int followAnimalWidth;
    public int followAnimalHeight;
    public int followAnimalX;
    public int followAnimalY;

    public int buttonWidth;
    public int buttonHeight;

    public int textInputWidth;
    public int textInputHeight;

    public MyLittleWorldSizes(int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        double panelRatio=0.35;
        double playgroundRatio=1-panelRatio;



        this.playgroundWidth = (int) (playgroundRatio*frameWidth);
        this.playgroundHeight = frameHeight;

        this.playgroundX = 0;
        this.playgroundY = 0;

        this.statisticsWidth = (int) (panelRatio*frameWidth);
        this.statisticsHeight = (int) (0.6*frameHeight);
        this.statisticsX = (int) (playgroundRatio*frameWidth);
        this.statisticsY = (int) (0.6*frameHeight);

        this.followAnimalWidth =  (int) (panelRatio*frameWidth);
        this.followAnimalHeight = (int) (0.6*frameHeight);
        this.followAnimalX = (int) (playgroundRatio*frameWidth);
        this.followAnimalY = 0;

        this.buttonWidth = (int) (frameWidth*panelRatio);
        this.buttonHeight = 50;

        this.textInputWidth = 100;
        this.textInputHeight = 40;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getPlaygroundWidth() {
        return playgroundWidth;
    }

    public int getPlaygroundHeight() {
        return playgroundHeight;
    }

    public int getPlaygroundX() {
        return playgroundX;
    }

    public int getPlaygroundY() {
        return playgroundY;
    }

    public int getStatisticsWidth() {
        return statisticsWidth;
    }

    public int getStatisticsHeight() {
        return statisticsHeight;
    }

    public int getStatisticsX() {
        return statisticsX;
    }

    public int getStatisticsY() {
        return statisticsY;
    }

    public int getFollowAnimalWidth() {
        return followAnimalWidth;
    }

    public int getFollowAnimalHeight() {
        return followAnimalHeight;
    }

    public int getFollowAnimalX() {
        return followAnimalX;
    }

    public int getFollowAnimalY() {
        return followAnimalY;
    }

    public int getButtonWidth() {
        return buttonWidth;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }

    public int getTextInputWidth() {
        return textInputWidth;
    }

    public int getTextInputHeight() {
        return textInputHeight;
    }
}
