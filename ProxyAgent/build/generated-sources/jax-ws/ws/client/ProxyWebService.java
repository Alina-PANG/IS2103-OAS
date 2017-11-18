
package ws.client;

import java.math.BigDecimal;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ProxyWebService", targetNamespace = "http://ws.session.ejb/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ProxyWebService {


    /**
     * 
     * @param bid
     * @param aid
     * @param cid
     * @throws BidLessThanIncrementException_Exception
     * @throws AuctionNotOpenException_Exception
     * @throws AuctionClosedException_Exception
     * @throws BidAlreadyExistException_Exception
     * @throws AuctionNotFoundException_Exception
     * @throws GeneralException_Exception
     * @throws CustomerNotFoundException_Exception
     * @throws NotEnoughCreditException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "createProxyBid", targetNamespace = "http://ws.session.ejb/", className = "ws.client.CreateProxyBid")
    @ResponseWrapper(localName = "createProxyBidResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.CreateProxyBidResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/createProxyBidRequest", output = "http://ws.session.ejb/ProxyWebService/createProxyBidResponse", fault = {
        @FaultAction(className = AuctionClosedException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createProxyBid/Fault/AuctionClosedException"),
        @FaultAction(className = AuctionNotOpenException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createProxyBid/Fault/AuctionNotOpenException"),
        @FaultAction(className = BidAlreadyExistException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createProxyBid/Fault/BidAlreadyExistException"),
        @FaultAction(className = NotEnoughCreditException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createProxyBid/Fault/NotEnoughCreditException"),
        @FaultAction(className = BidLessThanIncrementException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createProxyBid/Fault/BidLessThanIncrementException"),
        @FaultAction(className = GeneralException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createProxyBid/Fault/GeneralException"),
        @FaultAction(className = CustomerNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createProxyBid/Fault/CustomerNotFoundException"),
        @FaultAction(className = AuctionNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createProxyBid/Fault/AuctionNotFoundException")
    })
    public void createProxyBid(
        @WebParam(name = "bid", targetNamespace = "")
        ProxyBiddingEntity bid,
        @WebParam(name = "aid", targetNamespace = "")
        Long aid,
        @WebParam(name = "cid", targetNamespace = "")
        Long cid)
        throws AuctionClosedException_Exception, AuctionNotFoundException_Exception, AuctionNotOpenException_Exception, BidAlreadyExistException_Exception, BidLessThanIncrementException_Exception, CustomerNotFoundException_Exception, GeneralException_Exception, NotEnoughCreditException_Exception
    ;

    /**
     * 
     * @param aid
     * @param cid
     * @return
     *     returns ws.client.BidEntity
     * @throws BidNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewMyBidInAuction", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewMyBidInAuction")
    @ResponseWrapper(localName = "viewMyBidInAuctionResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewMyBidInAuctionResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/viewMyBidInAuctionRequest", output = "http://ws.session.ejb/ProxyWebService/viewMyBidInAuctionResponse", fault = {
        @FaultAction(className = BidNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewMyBidInAuction/Fault/BidNotFoundException")
    })
    public BidEntity viewMyBidInAuction(
        @WebParam(name = "aid", targetNamespace = "")
        Long aid,
        @WebParam(name = "cid", targetNamespace = "")
        Long cid)
        throws BidNotFoundException_Exception
    ;

    /**
     * 
     * @param password
     * @param username
     * @return
     *     returns ws.client.CustomerEntity
     * @throws CustomerNotPremiumException_Exception
     * @throws CustomerNotFoundException_Exception
     * @throws IncorrectPasswordException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "customerLogin", targetNamespace = "http://ws.session.ejb/", className = "ws.client.CustomerLogin")
    @ResponseWrapper(localName = "customerLoginResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.CustomerLoginResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/customerLoginRequest", output = "http://ws.session.ejb/ProxyWebService/customerLoginResponse", fault = {
        @FaultAction(className = CustomerNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/customerLogin/Fault/CustomerNotFoundException"),
        @FaultAction(className = IncorrectPasswordException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/customerLogin/Fault/IncorrectPasswordException"),
        @FaultAction(className = CustomerNotPremiumException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/customerLogin/Fault/CustomerNotPremiumException")
    })
    public CustomerEntity customerLogin(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws CustomerNotFoundException_Exception, CustomerNotPremiumException_Exception, IncorrectPasswordException_Exception
    ;

    /**
     * 
     * @param password
     * @param username
     * @return
     *     returns ws.client.CustomerEntity
     * @throws CustomerNotFoundException_Exception
     * @throws IncorrectPasswordException_Exception
     * @throws CustomerAlreadyPremiumException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registration", targetNamespace = "http://ws.session.ejb/", className = "ws.client.Registration")
    @ResponseWrapper(localName = "registrationResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.RegistrationResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/registrationRequest", output = "http://ws.session.ejb/ProxyWebService/registrationResponse", fault = {
        @FaultAction(className = CustomerNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/registration/Fault/CustomerNotFoundException"),
        @FaultAction(className = IncorrectPasswordException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/registration/Fault/IncorrectPasswordException"),
        @FaultAction(className = CustomerAlreadyPremiumException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/registration/Fault/CustomerAlreadyPremiumException")
    })
    public CustomerEntity registration(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws CustomerAlreadyPremiumException_Exception, CustomerNotFoundException_Exception, IncorrectPasswordException_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns ws.client.AuctionEntity
     * @throws AuctionNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewAuctionListDetails", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewAuctionListDetails")
    @ResponseWrapper(localName = "viewAuctionListDetailsResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewAuctionListDetailsResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/viewAuctionListDetailsRequest", output = "http://ws.session.ejb/ProxyWebService/viewAuctionListDetailsResponse", fault = {
        @FaultAction(className = AuctionNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewAuctionListDetails/Fault/AuctionNotFoundException")
    })
    public AuctionEntity viewAuctionListDetails(
        @WebParam(name = "id", targetNamespace = "")
        Long id)
        throws AuctionNotFoundException_Exception
    ;

    /**
     * 
     * @param productName
     * @return
     *     returns java.util.List<ws.client.AuctionEntity>
     * @throws AuctionNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewAuctionListByName", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewAuctionListByName")
    @ResponseWrapper(localName = "viewAuctionListByNameResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewAuctionListByNameResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/viewAuctionListByNameRequest", output = "http://ws.session.ejb/ProxyWebService/viewAuctionListByNameResponse", fault = {
        @FaultAction(className = AuctionNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewAuctionListByName/Fault/AuctionNotFoundException")
    })
    public List<AuctionEntity> viewAuctionListByName(
        @WebParam(name = "productName", targetNamespace = "")
        String productName)
        throws AuctionNotFoundException_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns java.math.BigDecimal
     * @throws GeneralException_Exception
     * @throws CustomerNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewCreditBalance", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewCreditBalance")
    @ResponseWrapper(localName = "viewCreditBalanceResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewCreditBalanceResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/viewCreditBalanceRequest", output = "http://ws.session.ejb/ProxyWebService/viewCreditBalanceResponse", fault = {
        @FaultAction(className = CustomerNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewCreditBalance/Fault/CustomerNotFoundException"),
        @FaultAction(className = GeneralException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewCreditBalance/Fault/GeneralException")
    })
    public BigDecimal viewCreditBalance(
        @WebParam(name = "id", targetNamespace = "")
        Long id)
        throws CustomerNotFoundException_Exception, GeneralException_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<ws.client.AuctionEntity>
     * @throws GeneralException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewAllAuctionListings", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewAllAuctionListings")
    @ResponseWrapper(localName = "viewAllAuctionListingsResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewAllAuctionListingsResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/viewAllAuctionListingsRequest", output = "http://ws.session.ejb/ProxyWebService/viewAllAuctionListingsResponse", fault = {
        @FaultAction(className = GeneralException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewAllAuctionListings/Fault/GeneralException")
    })
    public List<AuctionEntity> viewAllAuctionListings()
        throws GeneralException_Exception
    ;

    /**
     * 
     * @param id
     * @return
     *     returns java.util.List<ws.client.AuctionEntity>
     * @throws GeneralException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewWonAuctionListings", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewWonAuctionListings")
    @ResponseWrapper(localName = "viewWonAuctionListingsResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewWonAuctionListingsResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/viewWonAuctionListingsRequest", output = "http://ws.session.ejb/ProxyWebService/viewWonAuctionListingsResponse", fault = {
        @FaultAction(className = GeneralException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewWonAuctionListings/Fault/GeneralException")
    })
    public List<AuctionEntity> viewWonAuctionListings(
        @WebParam(name = "id", targetNamespace = "")
        Long id)
        throws GeneralException_Exception
    ;

    /**
     * 
     * @param bid
     * @param aid
     * @param cid
     * @throws BidLessThanIncrementException_Exception
     * @throws AuctionNotOpenException_Exception
     * @throws AuctionClosedException_Exception
     * @throws AuctionNotFoundException_Exception
     * @throws BidAlreadyExistException_Exception
     * @throws GeneralException_Exception
     * @throws CustomerNotFoundException_Exception
     * @throws NotEnoughCreditException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "createSnippingBid", targetNamespace = "http://ws.session.ejb/", className = "ws.client.CreateSnippingBid")
    @ResponseWrapper(localName = "createSnippingBidResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.CreateSnippingBidResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/createSnippingBidRequest", output = "http://ws.session.ejb/ProxyWebService/createSnippingBidResponse", fault = {
        @FaultAction(className = AuctionNotOpenException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createSnippingBid/Fault/AuctionNotOpenException"),
        @FaultAction(className = AuctionClosedException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createSnippingBid/Fault/AuctionClosedException"),
        @FaultAction(className = BidLessThanIncrementException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createSnippingBid/Fault/BidLessThanIncrementException"),
        @FaultAction(className = NotEnoughCreditException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createSnippingBid/Fault/NotEnoughCreditException"),
        @FaultAction(className = CustomerNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createSnippingBid/Fault/CustomerNotFoundException"),
        @FaultAction(className = AuctionNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createSnippingBid/Fault/AuctionNotFoundException"),
        @FaultAction(className = BidAlreadyExistException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createSnippingBid/Fault/BidAlreadyExistException"),
        @FaultAction(className = GeneralException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/createSnippingBid/Fault/GeneralException")
    })
    public void createSnippingBid(
        @WebParam(name = "bid", targetNamespace = "")
        BidEntity bid,
        @WebParam(name = "aid", targetNamespace = "")
        Long aid,
        @WebParam(name = "cid", targetNamespace = "")
        Long cid)
        throws AuctionClosedException_Exception, AuctionNotFoundException_Exception, AuctionNotOpenException_Exception, BidAlreadyExistException_Exception, BidLessThanIncrementException_Exception, CustomerNotFoundException_Exception, GeneralException_Exception, NotEnoughCreditException_Exception
    ;

    /**
     * 
     * @param aid
     * @return
     *     returns ws.client.BidEntity
     * @throws AuctionNotFoundException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewCurrentHighestBid", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewCurrentHighestBid")
    @ResponseWrapper(localName = "viewCurrentHighestBidResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewCurrentHighestBidResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/viewCurrentHighestBidRequest", output = "http://ws.session.ejb/ProxyWebService/viewCurrentHighestBidResponse", fault = {
        @FaultAction(className = AuctionNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewCurrentHighestBid/Fault/AuctionNotFoundException")
    })
    public BidEntity viewCurrentHighestBid(
        @WebParam(name = "aid", targetNamespace = "")
        Long aid)
        throws AuctionNotFoundException_Exception
    ;

    /**
     * 
     * @param aid
     * @return
     *     returns ws.client.BidEntity
     * @throws BidNotFoundException_Exception
     * @throws AuctionNotFoundException_Exception
     * @throws GeneralException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "viewWinningBid", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewWinningBid")
    @ResponseWrapper(localName = "viewWinningBidResponse", targetNamespace = "http://ws.session.ejb/", className = "ws.client.ViewWinningBidResponse")
    @Action(input = "http://ws.session.ejb/ProxyWebService/viewWinningBidRequest", output = "http://ws.session.ejb/ProxyWebService/viewWinningBidResponse", fault = {
        @FaultAction(className = BidNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewWinningBid/Fault/BidNotFoundException"),
        @FaultAction(className = AuctionNotFoundException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewWinningBid/Fault/AuctionNotFoundException"),
        @FaultAction(className = GeneralException_Exception.class, value = "http://ws.session.ejb/ProxyWebService/viewWinningBid/Fault/GeneralException")
    })
    public BidEntity viewWinningBid(
        @WebParam(name = "aid", targetNamespace = "")
        Long aid)
        throws AuctionNotFoundException_Exception, BidNotFoundException_Exception, GeneralException_Exception
    ;

}
