/*

* Copyright (c) Joan-Manuel Marques 2013. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This file is part of the practical assignment of Distributed Systems course.
*
* This code is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this code.  If not, see <http://www.gnu.org/licenses/>.
*/

package udp.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;

/**
 * @author Joan-Manuel Marques & Brayan Saiago Rojas
 *
 */

public class RemoteMapUDPservidor {
	
	public RemoteMapUDPservidor(int server_port, Map<String, String> map){
		LSimLogger.log(Level.INFO, "Inici RemoteMapUDPservidor ");
		LSimLogger.log(Level.INFO, "server_port: " + server_port);
		LSimLogger.log(Level.INFO, "map: " + map);

		// Server waits for requests a maximum time (timeout_time)
		Timer timer = new Timer();
		timer.schedule(
				new TimerTask() {
					@Override
					public void run() {
						System.exit(0);
					}
				},
				90000 // 90 seconds
				); 

		/* Implement UDP server's side */
		// Initialize socket with null value
        DatagramSocket socket = null;
	    try {
	    	// Create the socket with the server port
	        socket = new DatagramSocket(server_port);
	        // I let the server active as a infinite bucle waiting 
	        while (true) {
	        	// Receive the request with the password and define a 1024mb as a max weight
	            byte[] buffer = new byte[1024];
	            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
	            socket.receive(peticion);

	            // Get the password from the datagram received
	            String key = new String(peticion.getData(), 0, peticion.getLength());
	            LSimLogger.log(Level.TRACE, "Received key: " + key);

	            // Get the map password using by key
	            String value = map.get(key);
	            if (value == null) {
	                value = "";
	            }

	            // Return the response to the client with the ip address and port assigned
	            byte[] valueBytes = value.getBytes();
	            DatagramPacket respuesta = new DatagramPacket(
	                valueBytes,
	                valueBytes.length,
	                peticion.getAddress(),
	                peticion.getPort()
	            );
	            socket.send(respuesta);
	        }
	    // Catch the exception using java class
	    } catch (SocketException e) {
	        LSimLogger.log(Level.ERROR, "SocketException: " + e.getMessage());
	    } catch (IOException e) {
	        LSimLogger.log(Level.ERROR, "IOException: " + e.getMessage());
	    } finally {
	        // 6. Cerrar el socket para liberar recursos
	        if (socket != null && !socket.isClosed()) {
	            socket.close();
	        }
	    }
	}
}
