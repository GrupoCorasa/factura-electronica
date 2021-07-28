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

import mx.bigdata.sat.cfdi.examples.ExampleCFDFactory;
import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public final class TFDv1Test {

    private static PrivateKey key;

    private static X509Certificate cert;

    private static PrivateKey pacKey;

    private static X509Certificate pacCert;

    private TFDv1 tfd;

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

        pacKey = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
                new FileInputStream("resources/certs/CSD_ESCUELA_WILSON_ESQUIVEL_SA_DE_CV__EWE1709045U0_20190617_132205.key"),
                "12345678a"
        ).getKey();

        pacCert = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
                new FileInputStream("resources/certs/CSD_ESCUELA_WILSON_ESQUIVEL_SA_DE_CV__EWE1709045U0_20190617_132205s.cer")
        ).getKey();
    }

    @Before
    public void setupTFD() throws Exception {
        CFDv3 cfd = new CFDv3(ExampleCFDFactory.createComprobante(),
                "mx.bigdata.sat.cfdi.examples");
        cfd.sellar(key, cert);
        Date date = new GregorianCalendar(2011, 1, 7, 8, 51, 0).getTime();
        UUID uuid = UUID.fromString("843a05d7-207d-4adc-91e8-bda7175bcda3");
        tfd = new TFDv1(cfd, pacCert, uuid, date);
    }

    @Test
    public void testOriginalString() throws Exception {
        String cadena = "||1.0|843a05d7-207d-4adc-91e8-bda7175bcda3|2011-02-07T08:51:00|OD+eJsZEagd+iScEBGtbs85jyLeUfKUiujGWM8VF5X16nW0Ky3x2AwGS0PUz8UbWLhd3RUzl8EsUW/JbNKpAOvXH4rnpmOLkvn7c2M94fhmhQ9jKV3wF8yBDUvd2gK/99UlMEM8gEM5JlhbpOJPhktK0hRm0U/PDqgaUYO2V/MOOV3DUzRD+XcYQfakoOR/voYOenMUC9wht9GhiL5XuIyWClAmxYlhTb8CICoTOz3Q9OdHNaZUadHr6EJ1IriwF1OO0qKv+AV810mE68lGltd8gR6nKP1zCJqYn0Pq1LnQZurNwoxUvUh29bVwmh2PEkUOTob8uEbGoYssmnYQryQ==|30001000000400002450||";
        assertEquals(cadena, tfd.getCadenaOriginal());
    }

    @Test
    public void testStamp() throws Exception {
        tfd.timbrar(pacKey);
        String signature = "RPCfTkN87ifVCAswDQ65qkug10vA4hr6Hbo2gdtbkQP4gie32Mvuq003jef4dBOt87ykkjPeMc4f0lPjrGTtaFtjonAUAZOHUx4507DrRNHFPuwg96/lGS5OTVbiD24dJY6bKQNgxvg5vGs6oJ/nVJxiZ6pbwTXrj48+t9NXjvprWtiHBQoIswSgSAP2tx4vVdKNTFo43XCEws7WtphXf5+pqQcL+NWxp1En85wDL/pT7UbszJZhqSz/NkAfOSeXGaAgcQZDkn+UL92DinXkxnrAqYaNLlB7wUzT2BY0qcESkUV+3mkCTm41/7tJSCOUmQN2Ocoe5dHOYlMU7ZdP0w==";
        assertEquals(signature, tfd.getTimbre().getSelloSAT());
        BigInteger bi = pacCert.getSerialNumber();
        String certificateNum = new String(bi.toByteArray());
        assertEquals(certificateNum, tfd.getTimbre().getNoCertificadoSAT());
    }

    @Test
    public void testValidateVerify() throws Exception {
        tfd.timbrar(pacKey);
        tfd.validar();
        tfd.verificar();
    }

    @Test
    public void testValidateVerifyWithFile() throws Exception {
        CFDv3 cfd = new CFDv3(new FileInputStream("resources/xml/cfdv3_tfd.xml"));
        TFDv1 tfd = new TFDv1(cfd, pacCert);
        tfd.timbrar(pacKey);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        tfd.guardar(baos);
        CFDv3 cfd2 = new CFDv3(new ByteArrayInputStream(baos.toByteArray()));
        TFDv1 tfd2 = new TFDv1(cfd2, pacCert);
        tfd2.validar();
        tfd2.verificar();
    }
}
