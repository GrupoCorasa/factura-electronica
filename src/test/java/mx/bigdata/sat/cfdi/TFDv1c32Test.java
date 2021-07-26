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

import mx.bigdata.sat.cfdi.examples.ExampleCFDv32Factory;
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

public final class TFDv1c32Test {

    private static PrivateKey key;

    private static X509Certificate cert;

    private static PrivateKey pacKey;

    private static X509Certificate pacCert;

    private TFDv1c32 tfd;

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
        CFDv32 cfd = new CFDv32(ExampleCFDv32Factory.createComprobante(),
                "mx.bigdata.sat.cfdi.examples");
        cfd.sellar(key, cert);
        Date date = new GregorianCalendar(2011, 1, 7, 8, 51, 0).getTime();
        UUID uuid = UUID.fromString("843a05d7-207d-4adc-91e8-bda7175bcda3");
        tfd = new TFDv1c32(cfd, pacCert, uuid, date);
    }

    @Test
    public void testOriginalString() throws Exception {
        String cadena = "||1.0|843a05d7-207d-4adc-91e8-bda7175bcda3|2011-02-07T08:51:00|g0V1hv3unUXh6Hmg4T6G9se5Mx8bY4zoST0SLyhbUEG1tvt0M+1OWLF5Un6YVXXxm0AiW+U/9VIE58Wx4Lsa3LJ0wYDTONB4xPGIuEHxeI88ZcHDDwOGMA/8nugpquPV1it94eh8GVO9vogTxUgS29H/yWbACbwHwHQdsxklibxrhUH+Z2EwA0R/g+3iwRTEpVFduDM0o3J3d4ixPi+aPixVHyJxIZwO1l87B3mMd9CvMgn/y+bG7iP+xF9b6dcDmNFLYvBUInKp52ZaciYhfJVCEiqxKmZ2mPvdI/fNT+N4hpPZEq7XWDP25hGXcCXyrogjMds9tP4lsc6iLahlkA==|30001000000400002450||";
        assertEquals(cadena, tfd.getCadenaOriginal());
    }

    @Test
    public void testStamp() throws Exception {
        tfd.timbrar(pacKey);
        String signature = "FiUiTobAFdHJ4xDNnpBMlROTYYwqxKiKus7+tNU7AFYub7wHH4zGh8QTBOR30z+Y0J74q9lC2t4+OILt2AfhEqpNAeVtZ2bsVF5miES/uhN8jk2yoy6c3KqyLeQOooBA2bXh/lIVfwaxLYhSwLoXFN8hBf0/Tl2PLo7iEnwu+3zy2pBfRET9wKedIPNhzn++n7mtAXcPhxFqaJVugRZZpVl+8OjXBU6+7+HqDlxNLXr8B0p96zJSI6PI0k8ltG0Fp3vOrHf0w9xMrBGjJUIrk4XuQpfcYY+LHf9cWRN6eMVbG1vgZ6k6JUOKFBOQu50BXS5arUeig15Lz363aypqbw==";
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
