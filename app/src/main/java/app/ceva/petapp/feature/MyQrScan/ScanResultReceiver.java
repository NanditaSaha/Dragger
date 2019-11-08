package app.ceva.petapp.feature.MyQrScan;

import app.ceva.petapp.utils.NoScanResultException;

public interface ScanResultReceiver {
    public void scanResultData(String codeFormat, String codeContent);

    public void scanResultData(NoScanResultException noScanData);
}
