## securechat安全聊天室
采用自签名证书SelfSignedCertificate进行SSL的验证  
服务端采用自验证的X509证书和私钥，客户端采用InsecureTrustManagerFactory的keyManager  
仅为试验用途，绝不可用于生产  