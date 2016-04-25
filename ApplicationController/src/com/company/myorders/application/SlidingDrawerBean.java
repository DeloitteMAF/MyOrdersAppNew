package com.company.myorders.application;

import javax.el.MethodExpression;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.AdfmfSlidingWindowOptions;
import oracle.adfmf.framework.api.AdfmfSlidingWindowUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SlidingDrawerBean {

    protected static String slidingDrawerWindow;
    protected static boolean slidingDrawerToggleFlag;
    protected static boolean showTopAndBottomFacets;
    private AdfmfSlidingWindowOptions controlOptions;
    protected static boolean controlBarToggleFlag = false;
    protected static String controlBarHeight = "15%";
    private String slidingDrawerWindowSize = "0";


    public void setSlidingDrawerWindowSize(String slidingDrawerWindowSize) {
        String oldSlidingDrawerWindowSize = this.slidingDrawerWindowSize;
        this.slidingDrawerWindowSize = slidingDrawerWindowSize;
        propertyChangeSupport.firePropertyChange("slidingDrawerWindowSize", oldSlidingDrawerWindowSize,
                                                 slidingDrawerWindowSize);
    }

    public String getSlidingDrawerWindowSize() {
        try {

            String deviceOS = getDeviceOS();
            int availableWidth = Integer.parseInt(getDeviceAvailableWidth());
            int availableHeight = Integer.parseInt(getDeviceAvailableHeight());

            float diagonalSizeInFloat = Float.parseFloat(getDiaognalSize());
            int diagonalSizeInInt = Math.round(diagonalSizeInFloat);
            if (deviceOS != null && deviceOS.equals("iOS")) { //Check if the device OS is iOS 
                if (availableWidth > availableHeight) { // Check if the device is in landscape or portrait mode
                    if (diagonalSizeInInt < 6) { // Check the device screen diagonal size to set different values for tablet and smartphone
                        slidingDrawerWindowSize = "150"; 
                    } else {
                        slidingDrawerWindowSize = "150";
                    }

                } else {
                    
                    if (diagonalSizeInInt < 6) { // Check the device screen diagonal size to set different values for tablet and smartphone
                        slidingDrawerWindowSize = "160"; 
                    } else {
                        slidingDrawerWindowSize = "160";
                    }


                }


            } else if (deviceOS != null && deviceOS.equals("Android")) { //Check if the device OS is Android 
                if (availableWidth > availableHeight) { // Check if the device is in landscape or portrait mode
                    if (diagonalSizeInInt < 6) { // Check the device screen diagonal size to set different values for tablet and smartphone
                        slidingDrawerWindowSize = "350"; 
                    } else {
                        slidingDrawerWindowSize = "350";
                    }

                } else {
                    if (diagonalSizeInInt < 6) { // Check the device screen diagonal size to set different values for tablet and smartphone
                        slidingDrawerWindowSize = "360"; 
                    } else {
                        slidingDrawerWindowSize = "360";
                    }


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return slidingDrawerWindowSize;
    }


    public static void setControlBarHeight(String controlBarHeight) {
        SlidingDrawerBean.controlBarHeight = controlBarHeight;
    }

    public String getControlBarHeight() {
        controlBarHeight=_calculateControlBarHeight();
        return controlBarHeight;
    }


    public void setControlBarToggleFlag(boolean controlBarToggleFlag) {
        boolean oldControlBarToggleFlag = SlidingDrawerBean.controlBarToggleFlag;
        SlidingDrawerBean.controlBarToggleFlag = controlBarToggleFlag;
        propertyChangeSupport.firePropertyChange("controlBarToggleFlag", oldControlBarToggleFlag, controlBarToggleFlag);
    }

    public static boolean isControlBarToggleFlag() {
        return controlBarToggleFlag;
    }


    public void setShowTopAndBottomFacets(boolean showTopAndBottomFacets) {
        boolean oldShowTopAndBottomFacets = SlidingDrawerBean.showTopAndBottomFacets;
        SlidingDrawerBean.showTopAndBottomFacets = showTopAndBottomFacets;
        propertyChangeSupport.firePropertyChange("showTopAndBottomFacets", oldShowTopAndBottomFacets,
                                                 showTopAndBottomFacets);
    }

    public static boolean isShowTopAndBottomFacets() {
        return showTopAndBottomFacets;
    }


    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public void setSlidingDrawerToggleFlag(boolean slidingDrawerToggleFlag) {
        boolean oldSlidingDrawerToggleFlag = this.slidingDrawerToggleFlag;
        this.slidingDrawerToggleFlag = slidingDrawerToggleFlag;
        propertyChangeSupport.firePropertyChange("slidingDrawerToggleFlag", oldSlidingDrawerToggleFlag,
                                                 slidingDrawerToggleFlag);
    }

    public boolean isSlidingDrawerToggleFlag() {
        return slidingDrawerToggleFlag;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }


    public void setSlidingDrawerWindow(String slidingDrawerWindow) {
        String oldSlidingDrawerWindow = this.slidingDrawerWindow;
        this.slidingDrawerWindow = slidingDrawerWindow;
        propertyChangeSupport.firePropertyChange("slidingDrawerWindow", oldSlidingDrawerWindow, slidingDrawerWindow);
    }

    public String getSlidingDrawerWindow() {
        return slidingDrawerWindow;
    }

    public SlidingDrawerBean() {
        super();
        controlOptions = new AdfmfSlidingWindowOptions();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    private boolean springboardToggleFlag;
    private void setWindowOptions(String windowDirection, String windowStyle, String windowSize) {
        try {
            springboardToggleFlag = !springboardToggleFlag;
            AdfmfSlidingWindowOptions windowOptions = new AdfmfSlidingWindowOptions();
            windowOptions.setDirection(windowDirection);
            windowOptions.setStyle(windowStyle);
            windowOptions.setSize(windowSize);

            if (slidingDrawerWindow == null) {
                String slidingWindowDrawer = AdfmfSlidingWindowUtilities.create("SpringBoard"); //slidingWindowContent
                SlidingDrawerBean.slidingDrawerWindow = slidingWindowDrawer;
            }
            if (springboardToggleFlag) {
                AdfmfSlidingWindowUtilities.show(slidingDrawerWindow, windowOptions);
                AdfmfJavaUtilities.setELValue("#{applicationScope.isSprinboardOpen}", "Y");
            } else {
                AdfmfSlidingWindowUtilities.hide(slidingDrawerWindow);
                AdfmfJavaUtilities.setELValue("#{applicationScope.isSprinboardOpen}", "N");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void leftOverlay(ActionEvent actionEvent) {
        setShowTopAndBottomFacets(false);
        slidingDrawerWindowSize = getSlidingDrawerWindowSize();
        setWindowOptions(AdfmfSlidingWindowOptions.DIRECTION_LEFT, AdfmfSlidingWindowOptions.STYLE_OVERLAID,
                         slidingDrawerWindowSize);

    }

    public void rightOverlay(ActionEvent actionEvent) {
        setShowTopAndBottomFacets(false);
        slidingDrawerWindowSize = getSlidingDrawerWindowSize();
        setWindowOptions(AdfmfSlidingWindowOptions.DIRECTION_RIGHT, AdfmfSlidingWindowOptions.STYLE_OVERLAID,
                         slidingDrawerWindowSize);

    }

    public void topOverlay(ActionEvent actionEvent) {
        setShowTopAndBottomFacets(true);
        slidingDrawerWindowSize = getSlidingDrawerWindowSize();
        setWindowOptions(AdfmfSlidingWindowOptions.DIRECTION_TOP, AdfmfSlidingWindowOptions.STYLE_OVERLAID,
                         slidingDrawerWindowSize);

    }

    public void bottomOverlay(ActionEvent actionEvent) {
        setShowTopAndBottomFacets(true);
        slidingDrawerWindowSize = getSlidingDrawerWindowSize();
        setWindowOptions(AdfmfSlidingWindowOptions.DIRECTION_BOTTOM, AdfmfSlidingWindowOptions.STYLE_OVERLAID,
                         slidingDrawerWindowSize);

    }

    public void leftPin(ActionEvent actionEvent) {
        setShowTopAndBottomFacets(false);
        slidingDrawerWindowSize = getSlidingDrawerWindowSize();
        setWindowOptions(AdfmfSlidingWindowOptions.DIRECTION_LEFT, AdfmfSlidingWindowOptions.STYLE_PINNED,
                         slidingDrawerWindowSize);

    }

    public void rightPin(ActionEvent actionEvent) {
        setShowTopAndBottomFacets(false);
        slidingDrawerWindowSize = getSlidingDrawerWindowSize();
        setWindowOptions(AdfmfSlidingWindowOptions.DIRECTION_RIGHT, AdfmfSlidingWindowOptions.STYLE_PINNED,
                         slidingDrawerWindowSize);

    }

    public void topPin(ActionEvent actionEvent) {
        slidingDrawerWindowSize = getSlidingDrawerWindowSize();
        setWindowOptions(AdfmfSlidingWindowOptions.DIRECTION_TOP, AdfmfSlidingWindowOptions.STYLE_PINNED,
                         slidingDrawerWindowSize);
        setShowTopAndBottomFacets(true);
    }

    public void bottomPin(ActionEvent actionEvent) {
        slidingDrawerWindowSize = getSlidingDrawerWindowSize();
        setWindowOptions(AdfmfSlidingWindowOptions.DIRECTION_BOTTOM, AdfmfSlidingWindowOptions.STYLE_PINNED,
                         slidingDrawerWindowSize);
        setShowTopAndBottomFacets(true);
    }

    public void hideWindow(ActionEvent actionEvent) {
        AdfmfSlidingWindowUtilities.hide(slidingDrawerWindow);

    }

    public void gotoExamplesFeature(ActionEvent actionEvent) {
        AdfmfSlidingWindowUtilities.hide(slidingDrawerWindow);
        AdfmfContainerUtilities.gotoFeature("Examples");
    }

    public void gotoAboutFeature(ActionEvent actionEvent) {
        AdfmfSlidingWindowUtilities.hide(slidingDrawerWindow);
        AdfmfContainerUtilities.gotoFeature("About");
    }


    private static String remoteFeatureWindowId = null;
    private static String controlFeatureWindowId = null;

    public void launchRemoteURL(ActionEvent e) {

        AdfmfSlidingWindowOptions remoteOptions = new AdfmfSlidingWindowOptions();
        remoteOptions.setDirection(AdfmfSlidingWindowOptions.DIRECTION_BOTTOM);
        remoteOptions.setStyle(AdfmfSlidingWindowOptions.STYLE_OVERLAID);
        remoteOptions.setSize("100%");
        remoteFeatureWindowId = AdfmfSlidingWindowUtilities.create("RemoteURL");
        boolean b = AdfmfSlidingWindowUtilities.show(remoteFeatureWindowId, remoteOptions);

        //  AdfmfSlidingWindowOptions controlOptions = new AdfmfSlidingWindowOptions();
        controlOptions.setDirection(AdfmfSlidingWindowOptions.DIRECTION_TOP);
        controlOptions.setStyle(AdfmfSlidingWindowOptions.STYLE_OVERLAID);
        controlOptions.setSize(controlBarHeight);
        if (controlFeatureWindowId == null) {
            controlFeatureWindowId = AdfmfSlidingWindowUtilities.create("controlBar");
        }
        boolean b1 = AdfmfSlidingWindowUtilities.show(controlFeatureWindowId, controlOptions);

        this.setControlBarToggleFlag(true);
    }

    public void closeRemoteURL(ActionEvent e) {


        AdfmfSlidingWindowUtilities.destroy(remoteFeatureWindowId);
        AdfmfSlidingWindowUtilities.hide(controlFeatureWindowId);
        this.setControlBarToggleFlag(false);

    }

    public void setBarHeight(String newBarHeight) {

        controlOptions.setSize(newBarHeight);
        setControlBarHeight(newBarHeight);
        controlOptions.setDirection(AdfmfSlidingWindowOptions.DIRECTION_TOP);
        controlOptions.setStyle(AdfmfSlidingWindowOptions.STYLE_OVERLAID);
        if (controlBarToggleFlag) {
            AdfmfSlidingWindowUtilities.show(controlFeatureWindowId, controlOptions);
        }
    }


    private String _calculateControlBarHeight(){
    

        try {

            String deviceOS = getDeviceOS();
            int availableWidth = Integer.parseInt(getDeviceAvailableWidth());
            int availableHeight = Integer.parseInt(getDeviceAvailableHeight());

            float diagonalSizeInFloat = Float.parseFloat(getDiaognalSize());
            int diagonalSizeInInt = Math.round(diagonalSizeInFloat);
            if (deviceOS != null && deviceOS.equals("iOS")) { //Check if the device OS is iOS 
                if (availableWidth > availableHeight) { // Check if the device is in landscape or portrait mode
                    if (diagonalSizeInInt < 6) { // Check the device screen diagonal size to set different values for tablet and smartphone
                        controlBarHeight = "100"; 
                    } else {
                        controlBarHeight = "100";
                    }

                } else {
                    
                    if (diagonalSizeInInt < 6) { // Check the device screen diagonal size to set different values for tablet and smartphone
                        controlBarHeight = "100"; 
                    } else {
                        controlBarHeight = "100";
                    }


                }


            } else if (deviceOS != null && deviceOS.equals("Android")) { //Check if the device OS is Android 
                if (availableWidth > availableHeight) { // Check if the device is in landscape or portrait mode
                    if (diagonalSizeInInt < 6) { // Check the device screen diagonal size to set different values for tablet and smartphone
                        controlBarHeight = "156"; 
                    } else {
                        controlBarHeight = "156";
                    }

                } else {
                    if (diagonalSizeInInt < 6) { // Check the device screen diagonal size to set different values for tablet and smartphone
                        controlBarHeight = "156"; 
                    } else {
                        controlBarHeight = "156";
                    }


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return controlBarHeight;
        
        
        
    }
 

    private String getDeviceOS() {
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{deviceScope.device.os}", String.class);
        String deviceOS = (String) ve.getValue(AdfmfJavaUtilities.getELContext());
        return deviceOS;
    }


    private String getDeviceAvailableWidth() {
        ValueExpression ve =
            AdfmfJavaUtilities.getValueExpression("#{deviceScope.hardware.screen.availableWidth}", String.class);
        String deviceAvailableWidth = (String) ve.getValue(AdfmfJavaUtilities.getELContext());
        return deviceAvailableWidth;
    }

    private String getDeviceAvailableHeight() {
        ValueExpression ve =
            AdfmfJavaUtilities.getValueExpression("#{deviceScope.hardware.screen.availableHeight}", String.class);
        String deviceAvailableHeight = (String) ve.getValue(AdfmfJavaUtilities.getELContext());
        return deviceAvailableHeight;
    }

    private String getDiaognalSize() {
        ValueExpression ve =
            AdfmfJavaUtilities.getValueExpression("#{deviceScope.hardware.screen.diagonalSize}", String.class);
        String diagonalSize = (String) ve.getValue(AdfmfJavaUtilities.getELContext());
        return diagonalSize;
    }


}
