package mx.bigdata.sat.contabilidad_electronica.v11;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import mx.bigdata.sat.ce.v11.ce_sello_digital_cont_elec.schema.SelloDigitalContElec;
import mx.bigdata.sat.common.NamespacePrefixMapperImpl;
import mx.bigdata.sat.common.URIResolverImpl;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class SelloDigitalContElecv11 {

    private static final String XSLT = "/xslt/ce/v11/ce_sello_digital_cont_elec/SelloDigitalContElec.xslt";
    private static final String[] XSD = new String[]{
            "/xsd/ce/v11/ce_sello_digital_cont_elec/SelloDigitalContElec.xsd"
    };

    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    private static final String BASE_CONTEXT = "mx.bigdata.sat.ce.v11.ce_sello_digital_cont_elec.schema";

    private final static Joiner JOINER = Joiner.on(':');

    private final JAXBContext context;

    public static final ImmutableMap<String, String> PREFIXES =
            ImmutableMap.of("www.sat.gob.mx/esquemas/ContabilidadE/1_1/SelloDigitalContElec", "sellodigitalB",
                    "http://www.w3.org/2001/XMLSchema-instance", "xsi");

    private final Map<String, String> localPrefixes = Maps.newHashMap(PREFIXES);

    private TransformerFactory tf;

    final SelloDigitalContElec document;

    public SelloDigitalContElecv11(InputStream in, String... contexts) throws Exception {
        this.context = getContext(contexts);
        this.document = load(in);
    }

    public SelloDigitalContElecv11(SelloDigitalContElec balanza, String... contexts) throws Exception {
        this.context = getContext(contexts);
        this.document = copy(balanza);
    }

    public void addNamespace(String uri, String prefix) {
        localPrefixes.put(uri, prefix);
    }

    public void setTransformerFactory(TransformerFactory tf) {
        this.tf = tf;
        tf.setURIResolver(new URIResolverImpl());
    }

    public void validar() throws Exception {
        validar(null);
    }

    public void validar(ErrorHandler handler) throws Exception {
        SchemaFactory sf =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source[] schemas = new Source[XSD.length];
        for (int i = 0; i < XSD.length; i++) {
            schemas[i] = new StreamSource(getClass().getResourceAsStream(XSD[i]));
        }
        Schema schema = sf.newSchema(schemas);
        Validator validator = schema.newValidator();
        if (handler != null) {
            validator.setErrorHandler(handler);
        }
        validator.validate(new JAXBSource(context, document));
    }

    public void guardar(OutputStream out) throws Exception {
        Marshaller m = context.createMarshaller();
        m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapperImpl(localPrefixes));
        m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "www.sat.gob.mx/esquemas/ContabilidadE/1_1/SelloDigitalContElec " + "http://www.sat.gob.mx/esquemas/ContabilidadE/1_1/SelloDigitalContElec/SelloDigitalContElec.xsd");
        byte[] xmlHeaderBytes = XML_HEADER.getBytes(StandardCharsets.UTF_8);
        out.write(xmlHeaderBytes);
        m.marshal(document, out);
    }

    public String getCadenaOriginal() throws Exception {
        byte[] bytes = getOriginalBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static SelloDigitalContElec newComprobante(InputStream in) throws Exception {
        return load(in);
    }

//	public ComprobanteBase getComprobante() throws Exception {
//		return new CFDv32ComprobanteBase(doGetCatalogo());
//	}

    byte[] getOriginalBytes() throws Exception {
        JAXBSource in = new JAXBSource(context, document);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Result out = new StreamResult(baos);
        TransformerFactory factory = tf;
        if (factory == null) {
            factory = TransformerFactory.newInstance();
            factory.setURIResolver(new URIResolverImpl());
        }
        Transformer transformer = factory.newTransformer(new StreamSource(getClass().getResourceAsStream(XSLT)));
        transformer.transform(in, out);
        return baos.toByteArray();
    }

    // FUNCIONES AUXILIARES
    private static JAXBContext getContext(String[] contexts) throws Exception {
        List<String> ctx = Lists.asList(BASE_CONTEXT, contexts);
        return JAXBContext.newInstance(JOINER.join(ctx));
    }

    private static SelloDigitalContElec load(InputStream source, String... contexts)
            throws Exception {
        JAXBContext context = getContext(contexts);
        try {
            Unmarshaller u = context.createUnmarshaller();
            return (SelloDigitalContElec) u.unmarshal(source);
        } finally {
            source.close();
        }
    }

    // Defensive deep-copy
    private SelloDigitalContElec copy(SelloDigitalContElec balanza) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Marshaller m = context.createMarshaller();
        m.marshal(balanza, doc);
        Unmarshaller u = context.createUnmarshaller();
        return (SelloDigitalContElec) u.unmarshal(doc);
    }

}
