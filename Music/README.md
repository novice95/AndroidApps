# Music App

Music player app which will have control to -  
(1) Start a service to start playing music (store some music files in appropriate resource directory).  
(2) Stop playing the music.  
(3) Start another service that checks if the Internet connection is available, if so it talks a music player server and downloads the music files and save in appropriate files that are private to the app.  
(4) It will have broadcast receivers for the following actions BOOT_COMPLETED, POWER_CONNECTED, AIRPLANE_MODE.  
UI of the app is designed using Fragment.  
