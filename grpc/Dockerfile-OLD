FROM maven:3.6.2-jdk-8

# Install prerequisites
RUN apt-get update && apt-get install -y \
    build-essential \
  && rm -rf /var/lib/apt/lists/*

ENV GRPC_JAVA_VERSION=1.16.1 \
    PROTOBUF_VERSION=3.6.1

# Install PROTOBUF
# Only works for 3.0.0 and up
RUN curl -#sL "https://github.com/google/protobuf/releases/download/v$PROTOBUF_VERSION/protobuf-java-$PROTOBUF_VERSION.tar.gz" | tar -xz -C /usr/local \
  && cd /usr/local/protobuf-$PROTOBUF_VERSION && ./configure --prefix=/usr \
  && make -j 2 && make install \
  && rm -rf /usr/local/protobuf-$PROTOBUF_VERSION

# Compile GRPC JAVA
RUN curl -#sL "https://github.com/grpc/grpc-java/archive/v$GRPC_JAVA_VERSION.tar.gz" | tar -xz -C /usr/local \
  && cd /usr/local/grpc-java-$GRPC_JAVA_VERSION/compiler && ../gradlew java_pluginExecutable \
  && cp ./build/exe/java_plugin/protoc-gen-grpc-java /usr/local/bin/protoc-gen-grpc-java \
  && rm -rf /usr/local/grpc-java-$GRPC_JAVA_VERSION

ENTRYPOINT ["/usr/bin/mvn"]
CMD ["--version"]