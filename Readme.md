# Wrong orientation example

This is an example project with the BlinkID sdk that shows, that opening multiple activities back to back with a recognizer view is problematic.
This error only occurs on android 11 (API level 30).
Tested with BlinkId 5.0.7.

The project already contains a demo license key.

Steps to reproduce:
1. Deploy app on an android 11 device (API level 30)
2. Click "start scan button"
3. Scan any document
4. Wait until the scan view is opened again automatically
5. The camera view has now a wrong scale

![Example flow](./Example.png)

Adding a timeout of ~500ms before opening the scan activity a second time solves the problem in this case.