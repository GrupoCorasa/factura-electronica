/*
 *  Copyright 2011 BigData.mx
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
package mx.bigdata.sat.cfd;

import mx.bigdata.sat.cfd.examples.ExampleCFDv22Factory;
import mx.bigdata.sat.cfd.v22.schema.Comprobante;
import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class CFDv22Test {

    private static PrivateKey key;

    private static X509Certificate cert;

    @BeforeClass
    public static void loadKeys() throws Exception {
        key = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
                new FileInputStream("src/main/resources/certs/CSD_HERMANOS_ANZURES_ÑARVAEZ_SA_DE_CV_HAÑ930228SM9_20190617_132920.key"),
                "12345678a"
        ).getKey();

        cert = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
                new FileInputStream("src/main/resources/certs/CSD_HERMANOS_ANZURES_ÑARVAEZ_SA_DE_CV_HAÑ930228SM9_20190617_132920s.cer")
        ).getKey();
    }

    @Test
    public void testOriginalString() throws Exception {
        CFDv22 cfd = new CFDv22(ExampleCFDv22Factory.createComprobante());
        String cadena = "||2.2|ABCD|2|2012-05-03T14:11:36|49|2008|ingreso|UNA SOLA EXHIBICI\u00D3N|2000.00|0.00|2320.00|efectivo|Mexico|PAMC660606ER9|CONTRIBUYENTE PRUEBASEIS PATERNOSEIS MATERNOSEIS|PRUEBA SEIS|6|6|PUEBLA CENTRO|PUEBLA|PUEBLA|PUEBLA|M\u00C9XICO|72000|simplificado|CAUR390312S87|ROSA MAR\u00CDA CALDER\u00D3N UIRIEGAS|TOPOCHICO|52|JARDINES DEL VALLE|NUEVO LEON|M\u00E9xico|95465|1.00|Servicio|01|Asesoria Fiscal y administrativa|2000.00|2000.00|IVA|16.00|320.00|320.00||";
        assertEquals(cadena, cfd.getCadenaOriginal());
    }

    @Test
    public void testSign() throws Exception {
        CFDv22 cfd = new CFDv22(ExampleCFDv22Factory.createComprobante());
        Comprobante sellado = cfd.sellarComprobante(key, cert);
        String signature = "Z1BwQ9WAetlnaLPvMHFXKFMIFdb8pY2TXhxpsKPzDeEfumykSQw4RP8AoSC12gocktEjrIjGqIhYudzlB+vYoif/gZuuXk6G7oMUOw/ch6qy/w5FBEZsMZu45wWnq5AE0UoCxkgl2My/asBzrc5+lISSe1IG/+laaIaYMe+ZB8koTM4rQfC5eF6dW5zsqXxlkoXxjUf7CyKFQFFIA6qZGRMDxDGbQStWqhO9Tx2IqQ42xmhYiW/j7RnEhCgrLTRoN7HJQvRkjk07jvu7bGABue2DEQSIDr7ee02cmn1CoP4ev9hEC7JEEsT01iTkTSqWZikIRVDNMVLlU7bspH6Afw==";
        assertEquals(signature, sellado.getSello());
        String certificate = "MIIFvjCCA6agAwIBAgIUMzAwMDEwMDAwMDA0MDAwMDI0MzkwDQYJKoZIhvcNAQELBQAwggErMQ8wDQYDVQQDDAZBQyBVQVQxLjAsBgNVBAoMJVNFUlZJQ0lPIERFIEFETUlOSVNUUkFDSU9OIFRSSUJVVEFSSUExGjAYBgNVBAsMEVNBVC1JRVMgQXV0aG9yaXR5MSgwJgYJKoZIhvcNAQkBFhlvc2Nhci5tYXJ0aW5lekBzYXQuZ29iLm14MR0wGwYDVQQJDBQzcmEgY2VycmFkYSBkZSBjYWRpejEOMAwGA1UEEQwFMDYzNzAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBDSVVEQUQgREUgTUVYSUNPMREwDwYDVQQHDAhDT1lPQUNBTjERMA8GA1UELRMIMi41LjQuNDUxJTAjBgkqhkiG9w0BCQITFnJlc3BvbnNhYmxlOiBBQ0RNQS1TQVQwHhcNMTkwNjE3MjAxMjQ0WhcNMjMwNjE3MjAxMjQ0WjCB5TEkMCIGA1UEAxQbSEVSTUFOT1MgQU5aVVJFUyDRQVJWQUVaIFNBMSQwIgYDVQQpFBtIRVJNQU5PUyBBTlpVUkVTINFBUlZBRVogU0ExJDAiBgNVBAoUG0hFUk1BTk9TIEFOWlVSRVMg0UFSVkFFWiBTQTElMCMGA1UELRQcSEHROTMwMjI4U005IC8gS0FITzY0MTEwMUIzOTEeMBwGA1UEBRMVIC8gS0FITzY0MTEwMUhOVExLUzA2MSowKAYDVQQLFCFIRVJNQU5PUyBBTlpVUkVTINFBUlZBRVogU0EgREUgQ1YwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCos7aY0klINvTOdSPhhOkh69dhlqFxEUuskiG6XxA18HSUY8tHVhb7iKakJP8YE8KZZEc/ya5WFvFRT5034n+kM0hgVrG/ycIWdo3FFLcz/NdrdK2DvSIP0zv2EFbFrbHwqogS6GWWU2wzRNDC3s4iGnD8tDYOva9jnoauvMf/9oybNgJkWygo7RfDeYpghxqPbeJqclSmr8b6UuTde7cLYitRFsolFDF2cywrUssBR8Gm/9giWgNeSBiOn5zM1LgG9SI/kFK3D+xNeIDD/yL1HEIL7N+na9l9s8iLSfJWlNA7gTh2mMWlVllbA4DxG3atOrnX7fe6PirQCxrIKciHAgMBAAGjHTAbMAwGA1UdEwEB/wQCMAAwCwYDVR0PBAQDAgbAMA0GCSqGSIb3DQEBCwUAA4ICAQC6+4O5SILoD7lGHZIvXAnls2T3Xb8T9Wt1Ozz46kk4r8czTECW3bOmPu92bQO8vkDO33yYy86eirLjO9ewM2tWwgMt66oBkYrFzJXTTKA3z+F3cjk06aALFPjHG3LZjbqvja6pfoDHkh5DbXNFNdvzqBa0J/egXUmam9m8p5Mh94f7Tb7aHoDTGaYRdJoFV/h5yhtdIeEkrCwCXy9a5rnDrZ6GEYRW4WkSvaN2H4pW5ua3fKgPPig2Xj5Edj9zZ3E61nH7PuA+M7XbOHeTt+Bsnl6FlRvBI0Zimj8o2K7RIPMvjrtKpD7L6WBpTmQoPoDdUlw3I7WtzvrcqLzRnYVnehxjnhGyYfCNMOWOrli9deLGXEVpN5RP+xmjo/ihuwWSoydTPBMpXFc2Bj+2ZsazH1x+Jdmgs38yd819PJ3KE3P0K9K++xUXy6MEWMZ+BAEd1UQSO38hSDn3/xguzihT4zjCDWIJ8jQe0Zm3l93ih+C6DcNLqQHnmegJrqOtzzKLWZ4rfs2mHE1v0aqElnY753E+cxyNctPbKcVJGp5VkF/g5ssmr/OAz8Ofa1cxQShIm27E+gnvxmSetFPu/g5CkGpYj00ZcKgicvNCRoaNYc5wBEFH21PeYiVjkdplqZlRrsDHipQ1gYcqz/Vw8c3ZKG8zlVVvhlBbUNByDh/9nw==";
        assertEquals(certificate, sellado.getCertificado());
        String certificateNum = "30001000000400002439";
        assertEquals(certificateNum, sellado.getNoCertificado());
    }

    @Test
    public void testValidateVerify() throws Exception {
        CFDv22 cfd = new CFDv22(ExampleCFDv22Factory.createComprobante(2011));
        cfd.sellar(key, cert);
        cfd.validar();
        cfd.verificar();
    }

    @Test
    public void testValidateVerifyPrevious() throws Exception {
        CFDv22 cfd = new CFDv22(ExampleCFDv22Factory.createComprobante(2010));
        cfd.sellar(key, cert);
        cfd.validar();
        cfd.verificar();
    }

    @Test
    public void testValidateVerifyWithFile() throws Exception {
        CFD2 cfd = CFD2Factory.load(new File("resources/xml/cfdv22.xml"));
        cfd.sellar(key, cert);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        cfd.guardar(baos);
        CFD2 cfd2 = CFD2Factory.load(new ByteArrayInputStream(baos.toByteArray()));
        cfd2.validar();
        cfd2.verificar();
    }

    @Test
    public void testSellarComprobante() throws Exception {
        Comprobante c = CFDv22
                .newComprobante(new FileInputStream("resources/xml/cfdv2.xml"));
        CFDv22 cfd = new CFDv22(c);
        Comprobante sellado = cfd.sellarComprobante(key, cert);
        assertNotNull(sellado.getSello());
        assertNotNull(sellado.getNoCertificado());
        assertNotNull(sellado.getCertificado());
    }
}
