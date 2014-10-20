上一篇文章中介绍了android tv metro 框架， 今天赶上要做一个视频版FAQ， 把我的方法和大家共享一下。

注：在我fork出一个新的baseline： https://github.com/AiAndroid/android_tv_metro

1, 创建工程，你肯定会的起名为com.starfish.faq

2, 从MainActivity派生StarfishActivity，代码位置

  https://raw.githubusercontent.com/AiAndroid/android_tv_metro/master/android/Starfish/src/com/starfish/faq/StarfishActivity.java

3, 编写你自己的Loader， 简单派生，填写home json位置

  https://github.com/AiAndroid/android_tv_metro/blob/master/android/Starfish/src/com/starfish/faq/FAQLoader.java

然后，就是用baidu搜索几张图标，

编译搞定。

 

当然你还是需要编写你的具体FAQ数据结构， 以及怎么解析它。这取决于你的业务。

后续就进入你的具体业务范畴了。

代码位置

https://github.com/AiAndroid/android_tv_metro/tree/master/android/Starfish
