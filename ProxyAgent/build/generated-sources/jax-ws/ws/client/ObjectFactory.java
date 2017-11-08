
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

    private final static QName _CustomerAlreadyExistException_QNAME = new QName("http://ws.session.ejb/", "CustomerAlreadyExistException");
    private final static QName _CustomerNotFoundException_QNAME = new QName("http://ws.session.ejb/", "CustomerNotFoundException");
    private final static QName _DuplicateException_QNAME = new QName("http://ws.session.ejb/", "DuplicateException");
    private final static QName _GeneralException_QNAME = new QName("http://ws.session.ejb/", "GeneralException");
    private final static QName _IncorrectPasswordException_QNAME = new QName("http://ws.session.ejb/", "IncorrectPasswordException");
    private final static QName _AddressEntity_QNAME = new QName("http://ws.session.ejb/", "addressEntity");
    private final static QName _AuctionEntity_QNAME = new QName("http://ws.session.ejb/", "auctionEntity");
    private final static QName _BidEntity_QNAME = new QName("http://ws.session.ejb/", "bidEntity");
    private final static QName _CreateNewCustomerEntity_QNAME = new QName("http://ws.session.ejb/", "createNewCustomerEntity");
    private final static QName _CreateNewCustomerEntityResponse_QNAME = new QName("http://ws.session.ejb/", "createNewCustomerEntityResponse");
    private final static QName _CreditPackageEntity_QNAME = new QName("http://ws.session.ejb/", "creditPackageEntity");
    private final static QName _CreditTransactionEntity_QNAME = new QName("http://ws.session.ejb/", "creditTransactionEntity");
    private final static QName _CustomerEntity_QNAME = new QName("http://ws.session.ejb/", "customerEntity");
    private final static QName _CustomerLogin_QNAME = new QName("http://ws.session.ejb/", "customerLogin");
    private final static QName _CustomerLoginResponse_QNAME = new QName("http://ws.session.ejb/", "customerLoginResponse");
    private final static QName _PremiumCustomerEntity_QNAME = new QName("http://ws.session.ejb/", "premiumCustomerEntity");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomerAlreadyExistException }
     * 
     */
    public CustomerAlreadyExistException createCustomerAlreadyExistException() {
        return new CustomerAlreadyExistException();
    }

    /**
     * Create an instance of {@link CustomerNotFoundException }
     * 
     */
    public CustomerNotFoundException createCustomerNotFoundException() {
        return new CustomerNotFoundException();
    }

    /**
     * Create an instance of {@link DuplicateException }
     * 
     */
    public DuplicateException createDuplicateException() {
        return new DuplicateException();
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
     * Create an instance of {@link AddressEntity }
     * 
     */
    public AddressEntity createAddressEntity() {
        return new AddressEntity();
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
     * Create an instance of {@link CreateNewCustomerEntity }
     * 
     */
    public CreateNewCustomerEntity createCreateNewCustomerEntity() {
        return new CreateNewCustomerEntity();
    }

    /**
     * Create an instance of {@link CreateNewCustomerEntityResponse }
     * 
     */
    public CreateNewCustomerEntityResponse createCreateNewCustomerEntityResponse() {
        return new CreateNewCustomerEntityResponse();
    }

    /**
     * Create an instance of {@link CreditPackageEntity }
     * 
     */
    public CreditPackageEntity createCreditPackageEntity() {
        return new CreditPackageEntity();
    }

    /**
     * Create an instance of {@link CreditTransactionEntity }
     * 
     */
    public CreditTransactionEntity createCreditTransactionEntity() {
        return new CreditTransactionEntity();
    }

    /**
     * Create an instance of {@link CustomerEntity }
     * 
     */
    public CustomerEntity createCustomerEntity() {
        return new CustomerEntity();
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
     * Create an instance of {@link PremiumCustomerEntity }
     * 
     */
    public PremiumCustomerEntity createPremiumCustomerEntity() {
        return new PremiumCustomerEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerAlreadyExistException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "CustomerAlreadyExistException")
    public JAXBElement<CustomerAlreadyExistException> createCustomerAlreadyExistException(CustomerAlreadyExistException value) {
        return new JAXBElement<CustomerAlreadyExistException>(_CustomerAlreadyExistException_QNAME, CustomerAlreadyExistException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "DuplicateException")
    public JAXBElement<DuplicateException> createDuplicateException(DuplicateException value) {
        return new JAXBElement<DuplicateException>(_DuplicateException_QNAME, DuplicateException.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "addressEntity")
    public JAXBElement<AddressEntity> createAddressEntity(AddressEntity value) {
        return new JAXBElement<AddressEntity>(_AddressEntity_QNAME, AddressEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuctionEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "auctionEntity")
    public JAXBElement<AuctionEntity> createAuctionEntity(AuctionEntity value) {
        return new JAXBElement<AuctionEntity>(_AuctionEntity_QNAME, AuctionEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BidEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "bidEntity")
    public JAXBElement<BidEntity> createBidEntity(BidEntity value) {
        return new JAXBElement<BidEntity>(_BidEntity_QNAME, BidEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewCustomerEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createNewCustomerEntity")
    public JAXBElement<CreateNewCustomerEntity> createCreateNewCustomerEntity(CreateNewCustomerEntity value) {
        return new JAXBElement<CreateNewCustomerEntity>(_CreateNewCustomerEntity_QNAME, CreateNewCustomerEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewCustomerEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "createNewCustomerEntityResponse")
    public JAXBElement<CreateNewCustomerEntityResponse> createCreateNewCustomerEntityResponse(CreateNewCustomerEntityResponse value) {
        return new JAXBElement<CreateNewCustomerEntityResponse>(_CreateNewCustomerEntityResponse_QNAME, CreateNewCustomerEntityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditPackageEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "creditPackageEntity")
    public JAXBElement<CreditPackageEntity> createCreditPackageEntity(CreditPackageEntity value) {
        return new JAXBElement<CreditPackageEntity>(_CreditPackageEntity_QNAME, CreditPackageEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditTransactionEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "creditTransactionEntity")
    public JAXBElement<CreditTransactionEntity> createCreditTransactionEntity(CreditTransactionEntity value) {
        return new JAXBElement<CreditTransactionEntity>(_CreditTransactionEntity_QNAME, CreditTransactionEntity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "customerEntity")
    public JAXBElement<CustomerEntity> createCustomerEntity(CustomerEntity value) {
        return new JAXBElement<CustomerEntity>(_CustomerEntity_QNAME, CustomerEntity.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PremiumCustomerEntity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session.ejb/", name = "premiumCustomerEntity")
    public JAXBElement<PremiumCustomerEntity> createPremiumCustomerEntity(PremiumCustomerEntity value) {
        return new JAXBElement<PremiumCustomerEntity>(_PremiumCustomerEntity_QNAME, PremiumCustomerEntity.class, null, value);
    }

}
