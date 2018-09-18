## static file server
banyan用netty实现的双向文件服务器  
1.HttpResponseEncoder+HttpRequestDecoder+HttpObjectAggregator封装了http协议  
2.CorsHandler用于跨域  
3.QueryStringDecoder用于摘取url上的参数  
4.DefaultFileRegion用于将文件或者文件管道与channel绑定，实现0拷贝  
5.readHttpDataChunkByChunk方法展现了读取client上传的chunk分片文件时的操作样例  