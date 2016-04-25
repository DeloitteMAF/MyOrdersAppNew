package com.company.myorders.application;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.AdfmfSlidingWindowOptions;
import oracle.adfmf.framework.api.AdfmfSlidingWindowUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class WindowBean {
    private String springboardWindow;
    private boolean springboardToggleFlag;

    public WindowBean() {
        super();
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

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void showSpringboard(ActionEvent actionEvent) {
        springboardToggleFlag = !springboardToggleFlag;
        if (springboardWindow == null) {
            springboardWindow = AdfmfSlidingWindowUtilities.create("com.company.myorders.SpringBoard");
            this.setSpringboardWindow(springboardWindow);
        }

        AdfmfSlidingWindowOptions windowOptions = new AdfmfSlidingWindowOptions();

        windowOptions.setDirection(AdfmfSlidingWindowOptions.DIRECTION_LEFT);
        windowOptions.setStyle(AdfmfSlidingWindowOptions.STYLE_OVERLAID);
        if (springboardToggleFlag) {
            windowOptions.setSize("550");
            AdfmfSlidingWindowUtilities.show(springboardWindow, windowOptions);
            AdfmfJavaUtilities.setELValue("#{applicationScope.isSprinboardOpen}", "Y");
        } else {
            AdfmfSlidingWindowUtilities.hide(springboardWindow);
            AdfmfJavaUtilities.setELValue("#{applicationScope.isSprinboardOpen}", "N");
        }
    }

    public void setSpringboardToggleFlag(boolean springboardToggleFlag) {
        boolean oldSpringboardToggleFlag = this.springboardToggleFlag;
        this.springboardToggleFlag = springboardToggleFlag;
        propertyChangeSupport.firePropertyChange("springboardToggleFlag", oldSpringboardToggleFlag,
                                                 springboardToggleFlag);
    }

    public boolean isSpringboardToggleFlag() {
        return springboardToggleFlag;
    }

    public void setSpringboardWindow(String springboardWindow) {
        Object oldSpringboardWindow = this.springboardWindow;
        this.springboardWindow = springboardWindow;
        propertyChangeSupport.firePropertyChange("springboardWindow", oldSpringboardWindow, springboardWindow);
    }

    public String getSpringboardWindow() {
        return springboardWindow;
    }

    public void hideSpringboard(ActionEvent actionEvent) {
        springboardToggleFlag = !springboardToggleFlag;
        String springboardWindow = this.getSpringboardWindow();
        AdfmfSlidingWindowUtilities.hide(springboardWindow);
        AdfmfJavaUtilities.setELValue("#{applicationScope.isSprinboardOpen}", "N");
    }
    
    public String hideSpringboard(){
        springboardToggleFlag = !springboardToggleFlag;
        String springboardWindow = this.getSpringboardWindow();
        AdfmfSlidingWindowUtilities.hide(springboardWindow);
        AdfmfJavaUtilities.setELValue("#{applicationScope.isSprinboardOpen}", "N");
        return null;
    }

}
