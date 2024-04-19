# spring_security_jwt_oauth2

## Crypto RSA (Asimetrica - possui duas chaves: publica e privada)
Entrar dentro da pasta *resources*
#### Gerando a chave privada
```bash
# > diz o arquivo que será gerado
openssl genrsa > app.key
```

#### Chave publica
```bash
openssl openssl rsa -in app.key -pubout -out app.pub
```

#### criando variaveis pelo aplication.properties e atribuido os valores das chaves
```properties
jwt.public.key=classpath:app.pub
jwt.private.key=classpath:app.key
```

Site para gerar as chaves
https://cryptotools.net/rsagen  

## Rodando com docker
Subir uma imagem mysql
```bash
cd docker && docker compose up
```

#### projecto feito a partir do [Build & Run](https://www.youtube.com/watch?v=nDst-CRKt_k&t=4535s)
