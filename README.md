# 📡 Parte 1 — Programación con Sockets UDP

**Asignatura:** Redes y Aplicaciones Internet – Sistemas de Internet  
**Curso:** 2025-26/2  
**Autor:** Brayan Saiago Rojas  
**Universidad:** Universitat Oberta de Catalunya (UOC)

---

## 📋 Descripción

Implementación de una aplicación cliente-servidor basada en **sockets UDP** (no orientados a la conexión) en Java. El objetivo es que el cliente construya un mapa unificado con los datos distribuidos entre varios servidores, consultando clave por clave mediante datagramas UDP.

---

## 🏗️ Arquitectura

```
Cliente                         Servidor 1 (puerto 5836)
  │                                  │
  │── envia clave "k1" ────────────► │
  │◄─ recibe valor "One" ────────── │
  │                                  
  │                             Servidor 2 (puerto 5838)
  │                                  │
  │── envia clave "k2" ────────────► │
  │◄─ recibe valor "Thirty" ──────── │
```

**Mapa final del cliente:**
```
{k1=One, k2=Thirty, k3=Eleven, k4=Twenty-one}
```

---

## 📁 Ficheros del proyecto

| Fichero | Descripción |
|--------|-------------|
| `RemoteMapUDPclient.java` | ⭐ Modificado — Lógica del cliente UDP (método `get`) |
| `RemoteMapUDPservidor.java` | ⭐ Modificado — Lógica del servidor UDP |
| `UDPclientMain.java` | Clase principal del cliente (no modificada) |
| `UDPservidorMain.java` | Clase principal del servidor (no modificada) |
| `Key.java` | Clase para gestionar claves (no modificada) |

---

## ⚙️ Cómo funciona

### Servidor (`RemoteMapUDPservidor.java`)
1. Crea un `DatagramSocket` en el puerto configurado
2. Espera en bucle infinito datagramas de clientes
3. Extrae la clave recibida, busca el valor en su mapa local
4. Responde al cliente con el valor encontrado

### Cliente (`RemoteMapUDPclient.java`)
1. Crea un `DatagramSocket` sin puerto fijo (lo asigna el sistema)
2. Envía un datagrama con la clave al servidor correspondiente
3. Espera y recibe el datagrama de respuesta con el valor
4. Cierra el socket y devuelve el valor

---

## ▶️ Ejecución

### 1. Iniciar Servidor 1
```
Clase: UDPservidorMain
Argumento: 5836
```

### 2. Iniciar Servidor 2
```
Clase: UDPservidorMain
Argumento: 5838
```

### 3. Iniciar Cliente
```
Clase: UDPclientMain
Sin argumentos
```

> ⚠️ Los servidores deben estar en ejecución **antes** de lanzar el cliente.

---

## 🛠️ Tecnologías

- Java SE
- `java.net.DatagramSocket`
- `java.net.DatagramPacket`
- `java.net.InetAddress`
- Eclipse IDE
- DSLab (UOC)
