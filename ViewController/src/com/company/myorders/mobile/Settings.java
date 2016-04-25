package com.company.myorders.mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class Settings {
    private Boolean isPopupOpen=false;
    public Settings() {
    }

    public void locationVC(ValueChangeEvent valueChangeEvent) {
        
    }
    public String getCurrentFeature(){
        return AdfmfJavaUtilities.getFeatureId();
    }

    public void searchLocValChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
//        System.out.println("test: " +valueChangeEvent.getNewValue());
//        callButtonActionJS("cb2");
    }
    public void callButtonActionJS(String btn) {
        String featureID = AdfmfJavaUtilities.getFeatureId();
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "showPopup", new Object[] { btn });
    }
    public void onKeyDownSearch(String searchStr){
        System.out.println("test: " +searchStr);
        if(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.isPopupOpen}")==null){
            callButtonActionJS("cb2");            
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.isPopupOpen}", "Y");
        }
        AdfmfJavaUtilities.setELValue("#{viewScope.searchString}", searchStr);
        callButtonActionJS("cb1");

    }
    public void onFocusSearch(String searchVal){
        if(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.isPopupOpen}")==null){
            callButtonActionJS("cb2");            
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.isPopupOpen}", "Y");
            AdfmfJavaUtilities.setELValue("#{viewScope.searchString}", "");
            callButtonActionJS("cb1");            
        }
    }

    public void onSelectOfLoc(ActionEvent actionEvent) {
        // Add event code here...
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.isPopupOpen}", null);
        callButtonActionJS("cb3");  
        String txtValue = (String) AdfmfJavaUtilities.evaluateELExpression("#{preferenceScope.application.userPref.locationName}");
//        AdfmfJavaUtilities.setELValue("#{preferenceScope.application.userPref.location}", txtValue);        
        String featureID = AdfmfJavaUtilities.getFeatureId();
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(featureID, "setInputText", new Object[] { txtValue });
    }
    
    public void onFocusOutSearch(String searchVal){        
        if(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.isPopupOpen}")=="Y"){
//            callButtonActionJS("cb3");            
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.isPopupOpen}", null);
        }                    
    }
}
