
/**
 * CarRentalServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package localhost.carrental;

    /**
     *  CarRentalServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CarRentalServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CarRentalServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CarRentalServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for rentCar method
            * override this method for handling normal response from rentCar operation
            */
           public void receiveResultrentCar(
                    localhost.carrental.CarRentalServiceStub.RentCarResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from rentCar operation
           */
            public void receiveErrorrentCar(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAvailability method
            * override this method for handling normal response from getAvailability operation
            */
           public void receiveResultgetAvailability(
                    localhost.carrental.CarRentalServiceStub.GetAvailabilityResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAvailability operation
           */
            public void receiveErrorgetAvailability(java.lang.Exception e) {
            }
                


    }
    