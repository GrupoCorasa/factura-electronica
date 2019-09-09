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
                new FileInputStream("resources/certs/CSD_S_&_SOFTWARE_S.A_DE_C.V_S&S051221SE2_20190627_135134.key"),
                "12345678a"
        ).getKey();

        cert = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
                new FileInputStream("resources/certs/CSD_S_&_SOFTWARE_S.A_DE_C.V_S&S051221SE2_20190627_135134s.cer")
        ).getKey();

    }

    @Test
    public void testOriginalString() throws Exception {
        CFDv33 cfd = new CFDv33(ExampleCFDv33Factory.createComprobante());
        String cadena = "||3.3|F|12345|2017-07-01T00:00:00|02|20001000000200001428|Crédito a 20 días|1550.00|100.00|MXN|1|1798|I|PUE|03240|aB1cD|06|a0452045-89cb-4792-9cc0-153f21faab7f|PPL961114GZ1|PHARMA PLUS SA DE CV|601|PEPJ8001019Q8|JUAN PEREZ PEREZ|MEX|ResidenteExtranjero1|G01|10101501|GEN01|1.0|EA|CAPSULAS|VIBRAMICINA 100MG 10|775.00|775.00|0.16|002|Tasa|0.160000|124.00|67 52 3924 8060097|123456|10101501|GEN02|1.0|EA|BOTELLA|CLORUTO 500M|775.00|775.00|0.16|002|Tasa|0.160000|124.00|67 52 3924 8060097|123456|0|002|Tasa|0.160000|248.00|248.00||";
        assertEquals(cadena, cfd.getCadenaOriginal());
    }

    @Test
    public void testSign() throws Exception {
        CFDv33 cfd = new CFDv33(ExampleCFDv33Factory.createComprobante());
        cfd.sellar(key, cert);
        String signature = "fm0K56OXXd2tMvxycuRm2mUSKeGFkjCiWLhjXb3HNdyhrIjK8wt4/aVOE+N56QC4WzsdB/psbjJhqzHhEfLmmZexi28xcevvchwQygy6JoPWBroIvw79W7V7F3u67zK+VPfVn/EKrmcIj7ZKCZkChGRwuzk0oy9+yo4pjt5FOu8mpFYOIXlGTIGX7m7PzZ0JjHiVt06TGD6/1HA/vWuqU82mNKfeMZub7O9qCKr/fB1mX29aK/4uDL/yyGEuaOFB4zwtr29utFn+8vR8sxwnvMIGKxne/5c41xBlTgEioZzo06OQWE8l1ixN5utt0culTvXSKhMELgbjovOFvLQX0A==";
        assertEquals(signature, cfd.getComprobante().getSello());
        String certificate = "MIIFojCCA4qgAwIBAgIUMzAwMDEwMDAwMDA0MDAwMDI1NjgwDQYJKoZIhvcNAQELBQAwggErMQ8wDQYDVQQDDAZBQyBVQVQxLjAsBgNVBAoMJVNFUlZJQ0lPIERFIEFETUlOSVNUUkFDSU9OIFRSSUJVVEFSSUExGjAYBgNVBAsMEVNBVC1JRVMgQXV0aG9yaXR5MSgwJgYJKoZIhvcNAQkBFhlvc2Nhci5tYXJ0aW5lekBzYXQuZ29iLm14MR0wGwYDVQQJDBQzcmEgY2VycmFkYSBkZSBjYWRpejEOMAwGA1UEEQwFMDYzNzAxCzAJBgNVBAYTAk1YMRkwFwYDVQQIDBBDSVVEQUQgREUgTUVYSUNPMREwDwYDVQQHDAhDT1lPQUNBTjERMA8GA1UELRMIMi41LjQuNDUxJTAjBgkqhkiG9w0BCQITFnJlc3BvbnNhYmxlOiBBQ0RNQS1TQVQwHhcNMTkwNjI3MjExODI1WhcNMjMwNjI3MjExODI1WjCByTEeMBwGA1UEAxQVUyAmIFNPRlRXQVJFIFNBIERFIENWMR4wHAYDVQQpFBVTICYgU09GVFdBUkUgU0EgREUgQ1YxHjAcBgNVBAoUFVMgJiBTT0ZUV0FSRSBTQSBERSBDVjElMCMGA1UELRQcUyZTMDUxMjIxU0UyIC8gV0FUTTY0MDkxN0o0NTEeMBwGA1UEBRMVIC8gV0FUTTY0MDkxN01IR1RSUjAxMSAwHgYDVQQLFBdTICYgU09GVFdBUkUgUy5BIERFIEMuVjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAIquB4Sye4nr7pYjvNECsNq5+2zViYZw3DXR4gUWTNmVR42UbM41/e9wijCAbay2j+GQ7C6W/ArIy6KWCYUc/kaxz0MNDwgy+p/OgA4Xc8oD9/do9Gnqamf6eIYWd3YgwOE8x3c5ztb7S1cGmJLmLFOVnHaIw9UInqi3lQ/jVZ19X/BKBj+JhqnCYDKmhAb3oifkMmZpPOJP2NYZVrAdrc16bofNvL3gg7jZz5+gaLDhrml5VOCvrFNu3cQ7h6gfrfrG2kEjx376ILZnUtP0FdECqToYE8FwxAnmtrMzUZS9FCiXywkYTHrsGSgvwzfGqjuFixnxuP1YgxU4W5MK/okCAwEAAaMdMBswDAYDVR0TAQH/BAIwADALBgNVHQ8EBAMCBsAwDQYJKoZIhvcNAQELBQADggIBAB1ielLy+8wR7XiX2aoi+2eCBtHnxrqYkczvIXzQyL9lgrbDGAy8uYa0J6LB2VbWjd5ns+d37aCQiDZhk+ewLOkKR8+udliBSIHhY5Kqm0wRCMImPO/4xCb+X7uOxIW+ALrtQ/GmtxMIqBS0lbtXk6Pe1Mgl9jXdYj4ZX6uJVwLWBQ/kfKOS0YyqxZ6VLHdr7zT70FwCagk0UF+PRhY8/KwyvIijpQGudY7yHMQRX7wEByTxR+jcsSAc7CzFyE2U4a1TCbR2XU40pEeeiygYvnPQHQJdyBCCXT1jsGVB0aM2ahzZmDt1yB5XJJHCoIGsHmMddl9zP6nhHH+cRvfkDvs0GYrpjyndcbrMqOZTwGScU3rVoTbKNYpmovfs5rlwE9W1arlV0+Sk8TTieFD2RgtJHxHsVcpZ9M70PAT4eZLWKKcFQdNeMENSuK4GH3jaWkKYCfQuycW2CbgQwfaw8BiO6JNI+CtxWRsAyc757aM1LrLyUjI+VsxmFC1X7SGcHRY4uT6glXcnsGsVRNpjIOraP04fAvHsVvD1Od3l5eTQd5TF82Z8LsLobfr0lnZ5DnN7hE55Lwyx8I6fJYHH/rt+QXCejsVDPtQA/zTz4oJunCX6ohDazdaC03mkmOYNtKtKQdhKGO6JH/KJXFxs7Kdnf1YmL2TclXQpGbgXYkc/";
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
