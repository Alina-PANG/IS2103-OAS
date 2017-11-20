
package ws.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AuctionClosedException_QNAME = new QName("http://ws.session.ejb/", "AuctionClosedException");
    private final static QName _AuctionNotFoundException_QNAME = new QName("http://ws.session.ejb/", "AuctionNotFoundException");
    private final static QName _AuctionNotOpenException_QNAME = new QName("http://ws.session.ejb/", "AuctionNotOpenException");
    private final static QName _BidAlreadyExistException_QNAME = new QName("http://ws.session.ejb/", "BidAlreadyExistException");
    private final static QName _BidLessThanIncrementException_QNAME = new QName("http://ws.session.ejb/", "BidLessThanIncrementException");
    private final static QName _BidNotFoundException_QNAME = new QName("http://ws.session.ejb/", "BidNotFoundException");
    private final static QName _CustomerAlreadyPremiumException_QNAME = new QName("http://ws.session.ejb/", "CustomerAlreadyPremiumException");
    private final static QName _CustomerNotFoundException_QNAME = new QName("http://ws.session.ejb/", "CustomerNotFoundException");
    private final static QName _CustomerNotPremiumException_QNAME = new QName("http://ws.session.ejb/", "CustomerNotPremiumException");
    private final static QName _GeneralException_QNAME = new QName("http://ws.session.ejb/", "GeneralException");
    private final static QName _IncorrectPasswordException_QNAME = new QName("http://ws.session.ejb/", "IncorrectPasswordException");
    private final static QName _NotEnoughCreditException_QNAME = new QName("http://ws.session.ejb/", "NotEnoughCreditException");
    private final static QName _CreateProxyBid_QNAME = new QName("http://ws.session.ejb/", "createProxyBid");
    private final static QName _CreateProxyBidResponse_QNAME = new QName("http://ws.session.ejb/", "createProxyBidResponse");
    private final static QName _CreateSnippingBid_QNAME = new QName("http://ws.session.ejb/", "createSnippingBid");
    private final static QName _CreateSnippingBidResponse_QNAME = new QName("http://ws.session.ejb/", "createSnippingBidResponse");
    private final static QName _CustomerLogin_QNAME = new QName("http://ws.session.ejb/", "customerLogin");
    private final static QName _CustomerLoginResponse_QNAME = new QName("http://ws.session.ejb/", "customerLoginResponse");
    private final static QName _GetMyBidAmount_QNAME = new QName("http://ws.session.ejb/", "getMyBidAmount");
    private final static QName _GetMyBidAmountResponse_QNAME = new QName("http://ws.session.ejb/", "getMyBidAmountResponse");
    private final static QName _Registration_QNAME = new QName("http://ws.session.ejb/", "registration");
    private final static QName _RegistrationResponse_QNAME = new QName("http://ws.session.ejb/", "registrationResponse");
    private final static QName _ViewAllAuctionListings_QNAME = new QName("http://ws.session.ejb/", "viewAllAuctionListings");
    private final static QName _ViewAllAuctionListingsResponse_QNAME = new QName("http://ws.session.ejb/", "viewAllAuctionListingsResponse");
    private final static QName _ViewAuctionListByName_QNAME = new QName("http://ws.session.ejb/", "viewAuctionListByName");
    private final static QName _ViewAuctionListByNameResponse_QNAME = new QName("http://ws.session.ejb/", "viewAuctionListByNameResponse");
    private final static QName _ViewAuctionListDetails_QNAME = new QName("http://ws.session.ejb/", "viewAuctionListDetails");
    private final static QName _ViewAuctionListDetailsResponse_QNAME = new QName("http://ws.session.ejb/", "viewAuctionListDetailsResponse");
    private final static QName _ViewCreditBalance_QNAME = new QName("http://ws.session.ejb/", "viewCreditBalance");
    private final static QName _ViewCreditBalanceResponse_QNAME = new QName("http://ws.session.ejb/", "viewCreditBalanceResponse");
    private final static QName _ViewCurrentHighestBid_QNAME = new QName("http://ws.session.ejb/", "viewCurrentHighestBid");
    private final static QName _ViewCurrentHighestBidResponse_QNAME = new QName("http://ws.session.ejb/", "viewCurrentHighestBidResponse");
    private final static QName _ViewMyBidInAuction_QNAME = new QName("http://ws.session.ejb/", "viewMyBidInAuction");
    private final static QName _ViewMyBidInAuctionResponse_QNAME = new QName("http://ws.session.ejb/", "viewMyBidInAuctionResponse");
    private final static QName _ViewWonAuctionListings_QNAME = new QName("http://ws.session.ejb/", "viewWonAuctionListings");
    private final static QName _ViewWonAuctionListingsResponse_QNAME = new QName("http://ws.session.ejb/", "viewWonAuctionListingsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuctionClosedException }
     * 
     */
    public AuctionClosedException createAuctionClosedException() {
        return new AuctionClosedException();
    }

    /**
     * Create an instance of {@link AuctionNotFoundException }
     * 
     */
    public AuctionNotFoundException createAuctionNotFoundException() {
        return new AuctionNotFoundException();
    }

    /**
     * Create an instance of {@link AuctionNotOpenException }
     * 
     */
    public AuctionNotOpenException createAuctionNotOpenException() {
        return new AuctionNotOpenException();
    }

    /**
     * Create an instance of {@link BidAlreadyExistException }
     * 
     */
    public BidAlreadyExistException createBidAlreadyExistException() {
        return new BidAlreadyExistException();
    }

    /**
     * Create an instance of {@link BidLessThanIncrementException }
     * 
     */
    public BidLessThanIncrementException createBidLessThanIncrementException() {
        return new BidLessThanIncrementException();
    }

    /**
     * Create an instance of {@link BidNotFoundException }
     * 
     */
    public BidNotFoundException createBidNotFoundException() {
        return new BidNotFoundException();
    }

    /**
     * Create an instance of {@link CustomerAlreadyPremiumException }
     * 
     */
    public CustomerAlreadyPremiumException createCustomerAlreadyPremiumException() {
        return new CustomerAlreadyPremiumException();
    }

    /**
     * Create an instance of {@link CustomerNotFoundException }
     * 
     */
    public CustomerNotFoundException createCustomerNotFoundException() {
        return new CustomerNotFoundException();
    }

    /**
     * Create an instance of {@link CustomerNotPremiumException }
     * 
     */
    public CustomerNotPremiumException createCustomerNotPremiumException() {
        return new CustomerNotPremiumException();
    }

    /**
     * Create an instance of {@link GeneralException }
     * 
     */
    public GeneralException createGeneralException() {
        return new GeneralException();
    }

    /**
     * Create an instance of {@link IncorrectPasswordException }
     * 
     */
    public IncorrectPasswordException createIncorrectPasswordException() {
        return new IncorrectPasswordException();
    }

    /**
     * Create an instance of {@link NotEnoughCreditException }
     * 
     */
    public NotEnoughCreditException createNotEnoughCreditException() {
        return new NotEnoughCreditException();
    }

    /**
     * Create an instance of {@link CreateProxyBid }
     * 
     */
    public CreateProxyBid createCreateProxyBid() {
        return new CreateProxyBid();
    }

    /**
     * Create an instance of {@link CreateProxyBidResponse }
     * 
     */
    public CreateProxyBidResponse createCreateProxyBidResponse() {
        return new CreateProxyBidResponse();
    }

    /**
     * Create an instance of {@link CreateSnippingBid }
     * 
     */
    public CreateSnippingBid createCreateSnippingBid() {
        return new CreateSnippingBid();
    }

    /**
     * Create an instance of {@link CreateSnippingBidResponse }
     * 
     */
    public CreateSnippingBidResponse createCreateSnippingBidResponse() {
        return new CreateSnippingBidResponse();
    }

    /**
     * Create an instance of {@link CustomerLogin }
     * 
     */
    public CustomerLogin createCustomerLogin() {
        return new CustomerLogin();
    }

    /**
     * Create an instance of {@link CustomerLoginResponse }
     * 
     */
    public CustomerLoginResponse createCustomerLoginResponse() {
        return new CustomerLoginResponse();
    }

    /**
     * Create an instance of {@link GetMyBidAmount }
     * 
     */
    public GetMyBidAmount createGetMyBidAmount() {
        return new GetMyBidAmount();
    }

    /**
     * Create an instance of {@link GetMyBidAmountResponse }
     * 
     */
    public GetMyBidAmountResponse createGetMyBidAmountResponse() {
        return new GetMyBidAmountResponse();
    }

    /**
     * Create an instance of {@link Registration }
     * 
     */
    public Registration createRegistration() {
        return new Registration();
    }

    /**
     * Create an instance of {@link RegistrationResponse }
     * 
     */
    public RegistrationResponse createRegistrationResponse() {
        return new RegistrationResponse();
    }

    /**
     * Create an instance of {@link ViewAllAuctionListings }
     * 
     */
    public ViewAllAuctionListings createViewAllAuctionListings() {
        return new ViewAllAuctionListings();
    }

    /**
     * Create an instance of {@link ViewAllAuctionListingsResponse }
     * 
     */
    public ViewAllAuctionListingsResponse createViewAllAuctionListingsResponse() {
        return new ViewAllAuctionListingsResponse();
    }

    /**
     * Create an instance of {@link ViewAuctionListByName }
     * 
     */
    public ViewAuctionListByName createViewAuctionListByName() {
        return new ViewAuctionListByName();
    }

    /**
     * Create an instance of {@link ViewAuctionListByNameResponse }
     * 
     */
    public ViewAuctionListByNameResponse createViewAuctionListByNameResponse() {
        return new ViewAuctionListByNameResponse();
    }

    /**
     * Create an instance of {@link ViewAuctionListDetails }
     * 
     */
    public ViewAuctionListDetails createViewAuctionListDetails() {
        return new ViewAuctionListDetails();
    }

    /**
     * Create an instance of {@link ViewAuctionListDetailsResponse }
     * 
     */
    public ViewAuctionListDetailsResponse createViewAuctionListDetailsResponse() {
        return new ViewAuctionListDetailsResponse();
    }

    /**
     * Create an instance of {@link ViewCreditBalance }
     * 
     */
    public ViewCreditBalance createViewCreditBalance() {
        return new ViewCreditBalance();
    }

    /**
     * Create an instance of {@link ViewCreditBalanceResponse }
     * 
     */
    public ViewCreditBalanceResponse createViewCreditBalanceResponse() {
        return new ViewCreditBalanceResponse();
    }

    /**
     * Create an instance of {@link ViewCurrentHighestBid }
     * 
     */
    public ViewCurrentHighestBid createViewCurrentHighestBid() {
        return new ViewCurrentHighestBid();
    }

    /**
     * Create an instance of {@link ViewCurrentHighestBidResponse }
     * 
     */
    public ViewCurrentHighestBidResponse createViewCurrentHighestBidResponse() {
        return new ViewCurrentHighestBidResponse();
    }

    /**
     * Create an instance of {@link ViewMyBidInAuction }
     * 
     */
    public ViewMyBidInAuction createViewMyBidInAuction() {
        return new ViewMyBidInAuction();
    }

    /**
     * Create an instance of {@link ViewMyBidInAuctionResponse }
     * 
     */
    public ViewMyBidInAuctionResponse createViewMyBidInAuctionResponse() {
        return new ViewMyBidInAuctionResponse();
    }

    /**
     * Create an instance of {@link ViewWonAuctionListings }
     * 
     */
    public ViewWonAuctionListings createViewWonAuctionListings() {
        return new ViewWonAuctionListings();
    }

    /**
     * Create an instance of {@link ViewWonAuctionListingsResponse }
     * 
     */
    public ViewWonAuctionListingsResponse createViewWonAuctionListingsResponse() {
        return new ViewWonAuctionListingsResponse();
    }

    /**
     * Create an instance of {@link CustomerEntity }
     * 
     */
    public CustomerEntity createCustomerEntity() {
        return new CustomerEntity();
    }

    /**
     * Create an instance of {@link AuctionEntity }
     * 
     */
    public AuctionEntity createAuctionEntity() {
        return new AuctionEntity();
    }

    /**
     * Create an instance of {@link BidEntity }
     * 
     */
    public BidEntity createBidEntity() {
        return new BidEntity();
    }

    /**
     * Create an instance of {@link AddressEntity }
     * 
     */
    public AddressEntity createAddressEntity() {
        return new AddressEntity();
    }

    /**
     * Create an instance of {@link CreditTransactionEntity }
     * 
     */
    public CreditTransactionEntity createCreditTransactionEntity() {
        return new CreditTransactionEntity();
    }

    /**
     * Create an instance of {@link CreditPackageEntity }
     * 
     */
    public CreditPackageEntity createCreditPackageEntity() {
        return new CreditPackageEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuctionClosedException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "AuctionClosedException")
    public JAXBElement<AuctionClosedException> createAuctionClosedException(AuctionClosedException value) {
        return new JAXBElement<AuctionClosedException>(_AuctionClosedException_QNAME, AuctionClosedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuctionNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "AuctionNotFoundException")
    public JAXBElement<AuctionNotFoundException> createAuctionNotFoundException(AuctionNotFoundException value) {
        return new JAXBElement<AuctionNotFoundException>(_AuctionNotFoundException_QNAME, AuctionNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuctionNotOpenException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "AuctionNotOpenException")
    public JAXBElement<AuctionNotOpenException> createAuctionNotOpenException(AuctionNotOpenException value) {
        return new JAXBElement<AuctionNotOpenException>(_AuctionNotOpenException_QNAME, AuctionNotOpenException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BidAlreadyExistException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "BidAlreadyExistException")
    public JAXBElement<BidAlreadyExistException> createBidAlreadyExistException(BidAlreadyExistException value) {
        return new JAXBElement<BidAlreadyExistException>(_BidAlreadyExistException_QNAME, BidAlreadyExistException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BidLessThanIncrementException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "BidLessThanIncrementException")
    public JAXBElement<BidLessThanIncrementException> createBidLessThanIncrementException(BidLessThanIncrementException value) {
        return new JAXBElement<BidLessThanIncrementException>(_BidLessThanIncrementException_QNAME, BidLessThanIncrementException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BidNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "BidNotFoundException")
    public JAXBElement<BidNotFoundException> createBidNotFoundException(BidNotFoundException value) {
        return new JAXBElement<BidNotFoundException>(_BidNotFoundException_QNAME, BidNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerAlreadyPremiumException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "CustomerAlreadyPremiumException")
    public JAXBElement<CustomerAlreadyPremiumException> createCustomerAlreadyPremiumException(CustomerAlreadyPremiumException value) {
        return new JAXBElement<CustomerAlreadyPremiumException>(_CustomerAlreadyPremiumException_QNAME, CustomerAlreadyPremiumException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "CustomerNotFoundException")
    public JAXBElement<CustomerNotFoundException> createCustomerNotFoundException(CustomerNotFoundException value) {
        return new JAXBElement<CustomerNotFoundException>(_CustomerNotFoundException_QNAME, CustomerNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerNotPremiumException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "CustomerNotPremiumException")
    public JAXBElement<CustomerNotPremiumException> createCustomerNotPremiumException(CustomerNotPremiumException value) {
        return new JAXBElement<CustomerNotPremiumException>(_CustomerNotPremiumException_QNAME, CustomerNotPremiumException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneralException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "GeneralException")
    public JAXBElement<GeneralException> createGeneralException(GeneralException value) {
        return new JAXBElement<GeneralException>(_GeneralException_QNAME, GeneralException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncorrectPasswordException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "IncorrectPasswordException")
    public JAXBElement<IncorrectPasswordException> createIncorrectPasswordException(IncorrectPasswordException value) {
        return new JAXBElement<IncorrectPasswordException>(_IncorrectPasswordException_QNAME, IncorrectPasswordException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotEnoughCreditException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "NotEnoughCreditException")
    public JAXBElement<NotEnoughCreditException> createNotEnoughCreditException(NotEnoughCreditException value) {
        return new JAXBElement<NotEnoughCreditException>(_NotEnoughCreditException_QNAME, NotEnoughCreditException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProxyBid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createProxyBid")
    public JAXBElement<CreateProxyBid> createCreateProxyBid(CreateProxyBid value) {
        return new JAXBElement<CreateProxyBid>(_CreateProxyBid_QNAME, CreateProxyBid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProxyBidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createProxyBidResponse")
    public JAXBElement<CreateProxyBidResponse> createCreateProxyBidResponse(CreateProxyBidResponse value) {
        return new JAXBElement<CreateProxyBidResponse>(_CreateProxyBidResponse_QNAME, CreateProxyBidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSnippingBid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createSnippingBid")
    public JAXBElement<CreateSnippingBid> createCreateSnippingBid(CreateSnippingBid value) {
        return new JAXBElement<CreateSnippingBid>(_CreateSnippingBid_QNAME, CreateSnippingBid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSnippingBidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createSnippingBidResponse")
    public JAXBElement<CreateSnippingBidResponse> createCreateSnippingBidResponse(CreateSnippingBidResponse value) {
        return new JAXBElement<CreateSnippingBidResponse>(_CreateSnippingBidResponse_QNAME, CreateSnippingBidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "customerLogin")
    public JAXBElement<CustomerLogin> createCustomerLogin(CustomerLogin value) {
        return new JAXBElement<CustomerLogin>(_CustomerLogin_QNAME, CustomerLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "customerLoginResponse")
    public JAXBElement<CustomerLoginResponse> createCustomerLoginResponse(CustomerLoginResponse value) {
        return new JAXBElement<CustomerLoginResponse>(_CustomerLoginResponse_QNAME, CustomerLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyBidAmount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getMyBidAmount")
    public JAXBElement<GetMyBidAmount> createGetMyBidAmount(GetMyBidAmount value) {
        return new JAXBElement<GetMyBidAmount>(_GetMyBidAmount_QNAME, GetMyBidAmount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMyBidAmountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "getMyBidAmountResponse")
    public JAXBElement<GetMyBidAmountResponse> createGetMyBidAmountResponse(GetMyBidAmountResponse value) {
        return new JAXBElement<GetMyBidAmountResponse>(_GetMyBidAmountResponse_QNAME, GetMyBidAmountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Registration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "registration")
    public JAXBElement<Registration> createRegistration(Registration value) {
        return new JAXBElement<Registration>(_Registration_QNAME, Registration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "registrationResponse")
    public JAXBElement<RegistrationResponse> createRegistrationResponse(RegistrationResponse value) {
        return new JAXBElement<RegistrationResponse>(_RegistrationResponse_QNAME, RegistrationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewAllAuctionListings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewAllAuctionListings")
    public JAXBElement<ViewAllAuctionListings> createViewAllAuctionListings(ViewAllAuctionListings value) {
        return new JAXBElement<ViewAllAuctionListings>(_ViewAllAuctionListings_QNAME, ViewAllAuctionListings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewAllAuctionListingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewAllAuctionListingsResponse")
    public JAXBElement<ViewAllAuctionListingsResponse> createViewAllAuctionListingsResponse(ViewAllAuctionListingsResponse value) {
        return new JAXBElement<ViewAllAuctionListingsResponse>(_ViewAllAuctionListingsResponse_QNAME, ViewAllAuctionListingsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewAuctionListByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewAuctionListByName")
    public JAXBElement<ViewAuctionListByName> createViewAuctionListByName(ViewAuctionListByName value) {
        return new JAXBElement<ViewAuctionListByName>(_ViewAuctionListByName_QNAME, ViewAuctionListByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewAuctionListByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewAuctionListByNameResponse")
    public JAXBElement<ViewAuctionListByNameResponse> createViewAuctionListByNameResponse(ViewAuctionListByNameResponse value) {
        return new JAXBElement<ViewAuctionListByNameResponse>(_ViewAuctionListByNameResponse_QNAME, ViewAuctionListByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewAuctionListDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewAuctionListDetails")
    public JAXBElement<ViewAuctionListDetails> createViewAuctionListDetails(ViewAuctionListDetails value) {
        return new JAXBElement<ViewAuctionListDetails>(_ViewAuctionListDetails_QNAME, ViewAuctionListDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewAuctionListDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewAuctionListDetailsResponse")
    public JAXBElement<ViewAuctionListDetailsResponse> createViewAuctionListDetailsResponse(ViewAuctionListDetailsResponse value) {
        return new JAXBElement<ViewAuctionListDetailsResponse>(_ViewAuctionListDetailsResponse_QNAME, ViewAuctionListDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCreditBalance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewCreditBalance")
    public JAXBElement<ViewCreditBalance> createViewCreditBalance(ViewCreditBalance value) {
        return new JAXBElement<ViewCreditBalance>(_ViewCreditBalance_QNAME, ViewCreditBalance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCreditBalanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewCreditBalanceResponse")
    public JAXBElement<ViewCreditBalanceResponse> createViewCreditBalanceResponse(ViewCreditBalanceResponse value) {
        return new JAXBElement<ViewCreditBalanceResponse>(_ViewCreditBalanceResponse_QNAME, ViewCreditBalanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCurrentHighestBid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewCurrentHighestBid")
    public JAXBElement<ViewCurrentHighestBid> createViewCurrentHighestBid(ViewCurrentHighestBid value) {
        return new JAXBElement<ViewCurrentHighestBid>(_ViewCurrentHighestBid_QNAME, ViewCurrentHighestBid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCurrentHighestBidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewCurrentHighestBidResponse")
    public JAXBElement<ViewCurrentHighestBidResponse> createViewCurrentHighestBidResponse(ViewCurrentHighestBidResponse value) {
        return new JAXBElement<ViewCurrentHighestBidResponse>(_ViewCurrentHighestBidResponse_QNAME, ViewCurrentHighestBidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewMyBidInAuction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewMyBidInAuction")
    public JAXBElement<ViewMyBidInAuction> createViewMyBidInAuction(ViewMyBidInAuction value) {
        return new JAXBElement<ViewMyBidInAuction>(_ViewMyBidInAuction_QNAME, ViewMyBidInAuction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewMyBidInAuctionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewMyBidInAuctionResponse")
    public JAXBElement<ViewMyBidInAuctionResponse> createViewMyBidInAuctionResponse(ViewMyBidInAuctionResponse value) {
        return new JAXBElement<ViewMyBidInAuctionResponse>(_ViewMyBidInAuctionResponse_QNAME, ViewMyBidInAuctionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewWonAuctionListings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewWonAuctionListings")
    public JAXBElement<ViewWonAuctionListings> createViewWonAuctionListings(ViewWonAuctionListings value) {
        return new JAXBElement<ViewWonAuctionListings>(_ViewWonAuctionListings_QNAME, ViewWonAuctionListings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewWonAuctionListingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "viewWonAuctionListingsResponse")
    public JAXBElement<ViewWonAuctionListingsResponse> createViewWonAuctionListingsResponse(ViewWonAuctionListingsResponse value) {
        return new JAXBElement<ViewWonAuctionListingsResponse>(_ViewWonAuctionListingsResponse_QNAME, ViewWonAuctionListingsResponse.class, null, value);
    }

}
