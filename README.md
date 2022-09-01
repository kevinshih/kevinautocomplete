# kevinautocompleteportal



執行指令:

```
http://localhost:9003/
```



輸入search之後要按enter生效的話

需要將不同網站的https證書加到自己java裡面的security

可參考這個網站介紹

https://blog.csdn.net/HD243608836/article/details/118705725

```schell
keytool -import -alias abc -keystore cacerts -file D://abc.cer
```

```
keytool -list -keystore cacerts -alias abc
```

![img](https://lh4.googleusercontent.com/AITPdxzca8RkpvjkfoWoyJGRsvHHqS6GYd_V5VmhYu9R1_TV_3B-YsU0GUX3_G6eWxGa5cRz1zn7rQ1hZ5xris3J0_3NWL2HtNOaFhjSFg4k4JFth5YcnBLeSpWiv-W-5ZnBAl1Xd1oPWZNAGMDbTHWP6bPS-9dX2ydXmkAbedCzM7XXlwXm8T7pWw)





並且在HttpClient這支程式裡面

要call url之前

需要先設定好

```java
System.setProperty("javax.net.ssl.trustStore", "C:/Program Files/Java/jre1.8.0_333/lib/security/cacerts");
System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
```



這樣再啟動專案

才能夠call進這些https網址