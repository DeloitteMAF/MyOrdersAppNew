package com.company.myorders.mobile.model.service;


import java.util.ArrayList;

import java.util.List;

import oracle.ateam.sample.mobile.v2.persistence.util.EntityUtils;
import oracle.ateam.sample.mobile.v2.persistence.service.EntityCRUDService;

import com.company.myorders.mobile.model.AllOrders;

import java.util.stream.Collectors;

import java.util.stream.Stream;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;


/**
 *  Service class that provides CRUD and custom operations against the allOrders data object.
 *  The behavior of this class is driven by the allOrders classMappingDescriptor in persistence-mapping.xml.
 *
 *  You can customize and extend this behavior by overriding methods of the EntityCRUDService superclass, and/or
 *  creating a subclass of the local and remote persistence managers as configured in persistence-mapping.xml.
 */
public class AllOrdersService extends EntityCRUDService<AllOrders> {

    /**
     * Default constructor. If autoQuery is set to true in the classMappingDescriptor in persistence-mapping.xml, then
     * the findAll method will be executed and the allOrders list will be populated when this constructor is invoked.
     * If you created a data control for this service class, the data control will use this constructor, allowing you to
     * immediately show data in your user interface when accessing the data control for the first time.
     * By default, the findAll method will first query the local SQLite database for any rows and show these immediately in
     * the UI. Then the remote findAll method as configured in persistence-mapping.xml will be executed in the background,
     * and the UI will be automatically refreshed when the remote data have been processed and stored in the local SQLite
     * database.
     * If you want the user to wait until the remote data have been processed and not show local data first, you can set
     * the remoteReadInBackground attribute in the classMappingDescriptor to false.
     *
     * If you need programmatic access to the same instance of this class as used by the bean data control typically
     * created for this class, then you can use the following convenience method:
     *
     * AllOrdersService crudService = (AllOrdersService) EntityUtils.getEntityCRUDService(AllOrders.class);
     *
     * Note that calling this method might fire a remote method call when autoQuery is set to true in the
     * classMappingDescriptor and the data control has not been instantiated yet for the feature context in which you
     * execute the above call. Remember: each feature has its own class loader, bean data controls are NOT shared
     * accross features!
     */
    public AllOrdersService() {
    }

    /**
     * Use this constructor with autoQuery=false in Java code when you want to execute a method in this service class
     * without autoQuery as configured in persistence-mapping.xml taking place.
     */
    public AllOrdersService(boolean autoQuery) {
        super(autoQuery);
    }

    protected Class getEntityClass() {
        return AllOrders.class;
    }

    protected String getEntityListName() {
        return "allOrders";
    }

    public List<AllOrders> getAllOrders() {
//        AllOrders allOrdersNew=new AllOrders();
        if(AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.isFilterApplier}")=="Y"){
            Stream<AllOrders> allOrderRow=getEntityList().stream();
            String orderNo = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.fOrderNo}");
            String item = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.fOrderValue}");
            String po = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.fPO}");
            String status = (String) AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.fStatus}");       
            if(orderNo!=null){
                allOrderRow=allOrderRow.filter(allOrdersNew -> allOrdersNew.getOrderNumber().startsWith(orderNo));
            }else if(item!=null){
                allOrderRow=allOrderRow.filter(allOrdersNew -> allOrdersNew.getTotalOrderedValue().startsWith(item));
            }else if(po!=null){
                allOrderRow=allOrderRow.filter(allOrdersNew -> allOrdersNew.getCustPoNumber().startsWith(po));
            }else if(status!=null){
                allOrderRow=allOrderRow.filter(allOrdersNew -> allOrdersNew.getFlowStatusCode().equalsIgnoreCase(status));
            }  
            List<AllOrders> allOrdersList=allOrderRow.collect(Collectors.toList());
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.OrdersCount}", allOrdersList.size());
            return allOrdersList;
        }else{
            return getEntityList();
        }
       
    }

    /**
     * This method is automatically called when using the Create operation on the allOrders collection
     * in the data control palette. It gets a new allOrders instance as argument and adds this instance to the
     * allOrders list.
     * Do NOT drag and drop this method from the data control palette, use the Create operation instead to ensure
     * that iterator binding and allOrders list stay in sync.
     * @param index
     * @param allOrders
     */
    public void addAllOrders(int index, AllOrders allOrders) {
        addEntity(index, allOrders);
    }

    /**
     * This method is automatically called when using the Delete operation on the allOrders collection
     * in the data control palette. It removes the allOrders instance passed in from the allOrders list, deletes the
     * corresponding row from the database (if persisted) and calls the configured remove method on the remote
     * persistence manager.
     * Do NOT drag and drop this method from the data control palette, use the Delete operation instead to ensure
     * that iterator binding and allOrders list stay in sync.
     * @param allOrders
     */
    public void removeAllOrders(AllOrders allOrders) {
        removeEntity(allOrders);
    }

    /**
     * Inserts or updates a allOrders using the configured persistence managers.
     * The insert or update is determined by calling isNewEntity on the allOrders instance.
     * @param allOrders
     */
    public void saveAllOrders(AllOrders allOrders) {
        super.mergeEntity(allOrders);
    }

    /**
     * Retrieves all allOrders instances using the configured persistence managers and populates the allOrders list
     * with the result.
     * When this method is called for the first time, and a remote persistence manager is configured,
     * the data is fetched remotely and the local DB is populated with the results.
     */
    public void findAllAllOrders() {
        super.findAll();
    }

    /**
     * Retrieves all allOrders instances using the findAll method on the remote persistence manager
     * and populates the allOrders list
     */
    public void findAllAllOrdersRemote() {
        super.doRemoteFindAll();
    }

    /**
     * Retrieves the allOrders instances that match the searchValue filter using the configured persistence
     * managers and populates the allOrders list with the result.
     * By default, the search value is applied to all string attributes using a "startsWith" operator.
     * To customize the attributes on which the searchValue is applied, you can override method getQuickSearchAttributeNames.
     * If a find method is configured against the remote persistence manager, then this method will also
     * call this method.
     * @param searchValue
     */
    public void findAllOrders(String searchValue) {
        super.find(searchValue);
    }

    /**
     * Synchronizes all pending data sync actions using the remote persistence manager
     * @param inBackground
     */
    public void synchronizeAllOrders(Boolean inBackground) {
        super.synchronize(inBackground);
    }

    /**
     * Resets the values of the allOrders instance to the values as stored in the SQLite database. This method
     * will do nothing when the allOrders is not persisted to the database.
     * @param allOrders
     */
    public void resetAllOrders(AllOrders allOrders) {
        super.resetEntity(allOrders);
    }

    /**
     * Returns true when there are pending allOrders data sync actions. Returns false if there are no such actions.
     */
    public boolean getHasAllOrdersDataSynchActions() {
        return getDataSynchManager().getHasDataSynchActions();
    }


}


