# 📡 Part 1 — UDP Socket Programming

**Subject:** Internet Networks and Applications – Internet Systems  
**Academic Year:** 2025-26/2  
**Author:** Brayan Saiago Rojas  
**University:** Universitat Oberta de Catalunya (UOC)

---

## 📋 Description

Implementation of a client-server application based on **UDP sockets** (connectionless) in Java. The goal is for the client to build a unified map with data distributed across multiple servers, querying key by key using UDP datagrams.

---

## 🏗️ Architecture

```
Client                          Server 1 (port 5836)
  │                                  │
  │── sends key "k1" ──────────────► │
  │◄─ receives value "One" ───────── │
  │                                  
  │                             Server 2 (port 5838)
  │                                  │
  │── sends key "k2" ──────────────► │
  │◄─ receives value "Thirty" ─────── │
```

**Final client map:**
```
{k1=One, k2=Thirty, k3=Eleven, k4=Twenty-one}
```

---

## 📁 Project Files

| File | Description |
|------|-------------|
| `RemoteMapUDPclient.java` | ⭐ Modified — UDP client logic (`get` method) |
| `RemoteMapUDPservidor.java` | ⭐ Modified — UDP server logic |
| `UDPclientMain.java` | Client main class (not modified) |
| `UDPservidorMain.java` | Server main class (not modified) |
| `Key.java` | Key management class (not modified) |

---

## ⚙️ How It Works

### Server (`RemoteMapUDPservidor.java`)
1. Creates a `DatagramSocket` on the configured port
2. Waits in an infinite loop for client datagrams
3. Extracts the received key and looks up the value in its local map
4. Responds to the client with the found value

### Client (`RemoteMapUDPclient.java`)
1. Creates a `DatagramSocket` without a fixed port (assigned by the OS)
2. Sends a datagram with the key to the corresponding server
3. Waits and receives the response datagram with the value
4. Closes the socket and returns the value

---

## ▶️ How to Run

### 1. Start Server 1
```
Class: UDPservidorMain
Argument: 5836
```

### 2. Start Server 2
```
Class: UDPservidorMain
Argument: 5838
```

### 3. Start Client
```
Class: UDPclientMain
No arguments needed
```

> ⚠️ Both servers must be running **before** launching the client.

---

## 🛠️ Technologies

- Java SE
- `java.net.DatagramSocket`
- `java.net.DatagramPacket`
- `java.net.InetAddress`
- Eclipse IDE
- DSLab (UOC)
