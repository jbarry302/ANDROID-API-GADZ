package org.jamesdev.apitest.activities;

public interface QRCodeFoundListener {

    void onQrCodeFound(String qrCode);
    void qrCodeNotFound();
}
