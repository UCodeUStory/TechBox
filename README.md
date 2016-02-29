Vollery 网络请求框架


1、很多人认为框架是一门新技术，这里我带你们解开他的面纱。

其实任何框架都是对代码当中频繁使用的功能进行简单的封装，使得其他地方用到同样功能可以简单化应用，那很多人认为他也是个新技术，毕竟这是创造性的，因此也可以这么认为，但是在我看来任何框架都是对已有的技术基础上进行封装，只不过他们封装的层次不同，例如，一个Java的库和一个C的库比起来，很明显JAVA的库封装的层次更高而已。

2、那我们学习这种高级库的作用是什么呢？

①当我们快速开发的时候可能会很有帮助，不用自己去写
②学习他的设计模式，以便自己写出好的代码和框架
③了解他的核心实现，以便在使用过程中出现bug很好的解决

3、针对Vollery框架他给我们提供了那些便利？

Volley 构建了一套相对完整的符合 Http 语义的缓存机制。 
扩展性强。Volley 中大多是基于接口的设计，可配置性强。
一定程度符合 Http 规范，包括返回 ResponseCode(2xx、3xx、4xx、5xx）的处理，请求头的处理，缓存机制的支持等。并支持重试及优先级定义。
 默认 Android2.3 及以上基于 HttpURLConnection，2.3 以下基于 HttpClient 实现，这两者的区别及优劣在4.2.1 Volley中具体介绍。
 提供简便的图片加载工具。

4、使用步骤：
     新建一个请求，根据要请求的返回值来定，然后添加Response Listener和ErrorListener,创建一个请求队列，并不停的执行里面请求，将新建的请求添加进去。注意在Activity或Fragment结束的时候取消所有请求。如果是一次请求，在请求开始时，取消所有请求。

5、核心同能是通过HttpUrlConnection 和 HttpClient.

6、HttpUrlConnection 和HttpClient 区别？
  总之，在安卓开发上，虽然HttpClient更好地支持很多细节的控制（如代理、COOKIE、鉴权、压缩、连接池），但相应地对开发人员要求更高，代码写起来更复杂，普通开发人员很难做到对它很好地驾驭，官方的支持也越来越少；而HttpUrlConnection对大部分工作进行了包装，屏蔽了不需要的细节，更适合开发人员直接调用，而且官方对它的支持和优化也会越来越好。我们既然是做安卓应用的开发，自然要遵循安卓官方的指引，选用HttpUrlConnection。

至于使用哪一个发送请求，代码中通过判系统当前SDK环境，如果是2.3以后版本用HttpUrlConnection，应为其简单，如果是2.3以前版本用HttpClient，原因（2.3以前的版本中HttpUrlConnection 存在bug）

7、Volley：Volley 对外暴露的 API，通过 newRequestQueue(…) 函数新建并启动一个请求队列RequestQueue。

Request：表示一个请求的抽象类。StringRequest、JsonRequest、ImageRequest 都是它的子类，表示某种类型的请求。

RequestQueue：表示请求队列，里面包含一个CacheDispatcher(用于处理走缓存请求的调度线程)、NetworkDispatcher数组(用于处理走网络请求的调度线程)，一个ResponseDelivery(返回结果分发接口)，通过 start() 函数启动时会启动CacheDispatcher和NetworkDispatchers。

CacheDispatcher：一个线程，用于调度处理走缓存的请求。启动后会不断从缓存请求队列中取请求处理，队列为空则等待，请求处理结束则将结果传递给ResponseDelivery去执行后续处理。当结果未缓存过、缓存失效或缓存需要刷新的情况下，该请求都需要重新进入NetworkDispatcher去调度处理。

NetworkDispatcher：一个线程，用于调度处理走网络的请求。启动后会不断从网络请求队列中取请求处理，队列为空则等待，请求处理结束则将结果传递给ResponseDelivery去执行后续处理，并判断结果是否要进行缓存。

ResponseDelivery：返回结果分发接口，目前只有基于ExecutorDelivery的在入参 handler 对应线程内进行分发。

HttpStack：处理 Http 请求，返回请求结果。目前 Volley 中有基于 HttpURLConnection 的HurlStack和 基于 Apache HttpClient 的HttpClientStack。

Network：调用HttpStack处理请求，并将结果转换为可被ResponseDelivery处理的NetworkResponse。

Cache：缓存请求结果，Volley 默认使用的是基于 sdcard 的DiskBasedCache。NetworkDispatcher得到请求结果后判断是否需要存储在 Cache，CacheDispatcher会从 Cache 中取缓存结果。
