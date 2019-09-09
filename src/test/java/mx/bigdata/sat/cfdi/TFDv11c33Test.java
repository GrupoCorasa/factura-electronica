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
import mx.bigdata.sat.security.KeyLoaderEnumeration;
import mx.bigdata.sat.security.factory.KeyLoaderFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public final class TFDv11c33Test {

    private static PrivateKey key;

    private static X509Certificate cert;

    private static PrivateKey pacKey;

    private static X509Certificate pacCert;

    private TFDv11c33 tfd;

    @BeforeClass
    public static void loadKeys() throws Exception {
        key = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PRIVATE_KEY_LOADER,
                new FileInputStream("resources/certs/CSD_HERMANOS_ANZURES_ÑARVAEZ_SA_DE_CV_HAÑ930228SM9_20190617_132920.key"),
                "12345678a"
        ).getKey();

        cert = KeyLoaderFactory.createInstance(
                KeyLoaderEnumeration.PUBLIC_KEY_LOADER,
                new FileInputStream("resources/certs/CSD_HERMANOS_ANZURES_+æARVAEZ_SA_DE_CV_HA+æ930228SM9_20190617_132920s.cer")
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
        CFDv33 cfd = new CFDv33(ExampleCFDv33Factory.createComprobante());
        cfd.sellar(key, cert);
        Date date = new GregorianCalendar(2017, 6, 7, 8, 51, 0).getTime();
        UUID uuid = UUID.fromString("843a05d7-207d-4adc-91e8-bda7175bcda3");
        String PAC = "FLI081010EK2";
        String leyenda = "Leyenda opciones del Pac 01";
        tfd = new TFDv11c33(cfd, pacCert, uuid, date, PAC, leyenda);
    }

    @Test
    public void testOriginalString() throws Exception {
        String cadena = "||1.1|843a05d7-207d-4adc-91e8-bda7175bcda3|2017-06-07T08:51:00|FLI081010EK2|Leyenda opciones del Pac 01|O4Stv/YtkJqIiJ1HFm4ESgWFCRDOiSSI5GRyWK50JN3Alm5RjvgTqDvRkISWHnpOGXrodKsLJ01xtrZvywyeXj2JjMPGAuOABh4N7wtI428X2mAp9gnj3D//M+hD3aCObkSQHvodkc6o15yHKE4Pfd/E/mUxS/vo/WsuyCk3H04T4PNYb3lv1sa5Wu0C+by0cicqr+rLE+jgT+OJj136VX2Z02c20Y/zhCZ4+dwZ5Qc+8Q2njomf3/nqQj+44HOw4TH2U5Z0ZNl1wOoa5NCV4nFPF3KtfE5FYv5pkNc901TRLDZmJPR4sHBz6rq9U8kBitPq+wUhrqoPzQ7DUFS3/g==|30001000000400002450||";
        assertEquals(cadena, tfd.getCadenaOriginal());
    }

    @Test
    public void testStamp() throws Exception {
        tfd.timbrar(pacKey);
        String signature = "HbgqpQAK8L3QLx3ow1hELrvX3kF1SibcZcgAoJA11LNo/kzq8mqTGpuLXYsS1X9YDLHGazQWmcdTAwP01a3WqFTPOHX9G4OZObFtY8LCW71as3Zjv0g+1Z5TvTlB5pdcqAVuXfoeKQB+q2/3PfAb25joAImL/s+BTHrQwbJKlGfreq8CHUYlHUmV5PPQkMR8ps7CGw2CLSjkjXBbaz6D1riJMy5TSnwPdmBeBnwIz/Pd7NI+XvwUi+nS4OnrHzTbRghq6aBGhTpJXpLYVxrq0NxO4QVspazI135DwxMqI22LGiLpRU7jd2X/FbQ+uk+Q7+7YnT0G7YZ/lla1K+fddg==";
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

}
