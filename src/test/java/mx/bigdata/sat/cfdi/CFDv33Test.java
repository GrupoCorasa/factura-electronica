/*
 *  Copyright 2010 BigData.mx
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package mx.bigdata.sat.cfdi;

import mx.bigdata.sat.cfdi.examples.ExampleCFDv33Factory;
import mx.bigdata.sat.cfdi.v33.schema.Comprobante;
import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class CFDv33Test {

    private static PrivateKey key;

    private static X509Certificate cert;

    @BeforeClass
    public static void loadKeys() throws Exception {
        key = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
                new FileInputStream("src/main/resources/certs/CSD_HERMANOS_ANZURES_—ARVAEZ_SA_DE_CV_HA—930228SM9_20190617_132920.key"),
                "12345678a"
        ).getKey();

        cert = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
                new FileInputStream("src/main/resources/certs/CSD_HERMANOS_ANZURES_—ARVAEZ_SA_DE_CV_HA—930228SM9_20190617_132920s.cer")
        ).getKey();
    }

    @Test
    public void testOriginalString() throws Exception {
        CFDv33 cfd = new CFDv33(ExampleCFDv33Factory.createComprobante());
        String cadena = "||3.3|F|12345|2017-07-01T00:00:00|02|20001000000200001428|Cr√©dito a 20 d√≠as|1550.00|100.00|MXN|1|1798|I|PUE|03240|aB1cD|06|a0452045-89cb-4792-9cc0-153f21faab7f|PPL961114GZ1|PHARMA PLUS SA DE CV|601|PEPJ8001019Q8|JUAN PEREZ PEREZ|MEX|ResidenteExtranjero1|G01|10101501|GEN01|1.0|EA|CAPSULAS|VIBRAMICINA 100MG 10|775.00|775.00|0.16|002|Tasa|0.160000|124.00|67 52 3924 8060097|123456|10101501|GEN02|1.0|EA|BOTELLA|CLORUTO 500M|775.00|775.00|0.16|002|Tasa|0.160000|124.00|67 52 3924 8060097|123456|0|002|Tasa|0.160000|248.00|248.00||";
        assertEquals(cadena, cfd.getCadenaOriginal());
    }

    @Test
    public void testSign() throws Exception {
        CFDv33 cfd = new CFDv33(ExampleCFDv33Factory.createComprobante());
        cfd.sellar(key, cert);
        String signature = "O3OzI30eNbuEnaj5BBXzStIdmva0TrL5vGLeDs1uIADCyexMays7+imIShfwXMSlg2OgAgDZoVI1GgkIN90zHJBrwpq08XLLDKzxrBl7rpPnKnE93uVSG5Z3/UEJZpmwtytD+bQhu/C8N+jydU3NUYqDwANvlXDbrgjUGEoyq6YQNgl4lP1dvfxFyfbnxpD6XCaFU8U5a69kG0/ral9CTctVM1nFnRYeP8KqETMuzyZiSFt0fYr+/DqPyCzPT5SbR7Q43y+YPge85dUzU6OW/P5Z4VTWgnjIOapn7kDWmxwD4oFsqRaGoGLQB/HpGEIedYFxrsGhEga5N9dgS/DPIQ==";
        assertEquals(signature, cfd.getComprobante().getSello());
        String certificate = "MIIFvjCCA6agAwIBAgIUMzAwMDEwMDAwMDA0MDAwMDI0MzkwDQYJKoZIhvcNAQELBQAwggErMQ8wDQYDVQQDDAZBQyBVQVQxLjAsBgNVBAoMJVNFUlZJQ0lPIERFIEFETUlOSVNUUkFDSU9OIFRSSUJVVEFSSUExGjAYBgNVBAsMEVNBVC1JRVMgQXV0aG9yaXR5MSgwJgYJKoZIhvcNAQkBFhlvc2Nhci5tYXJ0aW5lekBzYXQuZ29iLm14MR0wGwYDVQQJDBQzcmEgY2VycmFkYSBkZSBjYWRpejEOMAwGA1UEEQwFMDYzNzAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBDSVVEQUQgREUgTUVYSUNPMREwDwYDVQQHDAhDT1lPQUNBTjERMA8GA1UELRMIMi41LjQuNDUxJTAjBgkqhkiG9w0BCQITFnJlc3BvbnNhYmxlOiBBQ0RNQS1TQVQwHhcNMTkwNjE3MjAxMjQ0WhcNMjMwNjE3MjAxMjQ0WjCB5TEkMCIGA1UEAxQbSEVSTUFOT1MgQU5aVVJFUyDRQVJWQUVaIFNBMSQwIgYDVQQpFBtIRVJNQU5PUyBBTlpVUkVTINFBUlZBRVogU0ExJDAiBgNVBAoUG0hFUk1BTk9TIEFOWlVSRVMg0UFSVkFFWiBTQTElMCMGA1UELRQcSEHROTMwMjI4U005IC8gS0FITzY0MTEwMUIzOTEeMBwGA1UEBRMVIC8gS0FITzY0MTEwMUhOVExLUzA2MSowKAYDVQQLFCFIRVJNQU5PUyBBTlpVUkVTINFBUlZBRVogU0EgREUgQ1YwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCos7aY0klINvTOdSPhhOkh69dhlqFxEUuskiG6XxA18HSUY8tHVhb7iKakJP8YE8KZZEc/ya5WFvFRT5034n+kM0hgVrG/ycIWdo3FFLcz/NdrdK2DvSIP0zv2EFbFrbHwqogS6GWWU2wzRNDC3s4iGnD8tDYOva9jnoauvMf/9oybNgJkWygo7RfDeYpghxqPbeJqclSmr8b6UuTde7cLYitRFsolFDF2cywrUssBR8Gm/9giWgNeSBiOn5zM1LgG9SI/kFK3D+xNeIDD/yL1HEIL7N+na9l9s8iLSfJWlNA7gTh2mMWlVllbA4DxG3atOrnX7fe6PirQCxrIKciHAgMBAAGjHTAbMAwGA1UdEwEB/wQCMAAwCwYDVR0PBAQDAgbAMA0GCSqGSIb3DQEBCwUAA4ICAQC6+4O5SILoD7lGHZIvXAnls2T3Xb8T9Wt1Ozz46kk4r8czTECW3bOmPu92bQO8vkDO33yYy86eirLjO9ewM2tWwgMt66oBkYrFzJXTTKA3z+F3cjk06aALFPjHG3LZjbqvja6pfoDHkh5DbXNFNdvzqBa0J/egXUmam9m8p5Mh94f7Tb7aHoDTGaYRdJoFV/h5yhtdIeEkrCwCXy9a5rnDrZ6GEYRW4WkSvaN2H4pW5ua3fKgPPig2Xj5Edj9zZ3E61nH7PuA+M7XbOHeTt+Bsnl6FlRvBI0Zimj8o2K7RIPMvjrtKpD7L6WBpTmQoPoDdUlw3I7WtzvrcqLzRnYVnehxjnhGyYfCNMOWOrli9deLGXEVpN5RP+xmjo/ihuwWSoydTPBMpXFc2Bj+2ZsazH1x+Jdmgs38yd819PJ3KE3P0K9K++xUXy6MEWMZ+BAEd1UQSO38hSDn3/xguzihT4zjCDWIJ8jQe0Zm3l93ih+C6DcNLqQHnmegJrqOtzzKLWZ4rfs2mHE1v0aqElnY753E+cxyNctPbKcVJGp5VkF/g5ssmr/OAz8Ofa1cxQShIm27E+gnvxmSetFPu/g5CkGpYj00ZcKgicvNCRoaNYc5wBEFH21PeYiVjkdplqZlRrsDHipQ1gYcqz/Vw8c3ZKG8zlVVvhlBbUNByDh/9nw==";
        assertEquals(certificate, cfd.doGetComprobante().getCertificado());
        BigInteger bi = cert.getSerialNumber();
        String certificateNum = new String(bi.toByteArray());
        assertEquals(certificateNum, cfd.doGetComprobante().getNoCertificado());
    }

    @Test
    public void testValidateVerify() throws Exception {
        CFDv33 cfd = new CFDv33(ExampleCFDv33Factory.createComprobante());
        cfd.sellar(key, cert);
        cfd.validar();
        cfd.verificar();
    }

    @Test
    public void testValidateVerifyWithFile() throws Exception {
        CFDI cfd = CFDIFactory.load33(new File("resources/xml/cfdv33.xml"));
        cfd.sellar(key, cert);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        cfd.guardar(baos);
        CFDI cfd2 = CFDIFactory.load33(new ByteArrayInputStream(baos.toByteArray()));
        cfd2.validar();
        cfd2.verificar();
    }

    @Test
    public void testSellarComprobante() throws Exception {
        Comprobante c = CFDv33.newComprobante(new FileInputStream("resources/xml/cfdv33.xml"));
        CFDv33 cfd = new CFDv33(c);
        Comprobante sellado = cfd.sellarComprobante(key, cert);
        assertNotNull(sellado.getSello());
        assertNotNull(sellado.getNoCertificado());
        assertNotNull(sellado.getCertificado());
    }

}
