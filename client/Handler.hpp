#ifndef HANDLER_HPP
#define HANDLER_HPP

#include <winsock2.h>
#include <ws2tcpip.h>
#include <iostream>
#include <vector>
#include <atomic>
#include <Windows.h>
#include <WS2tcpip.h>
#include "../utils/Marshaller.hpp"

#pragma comment(lib, "Ws2_32.lib")

using namespace std;

class Handler
{
private:
    int BUFFER_SIZE;
    double PACKET_SEND_LOSS_PROB;
    double PACKET_RECV_LOSS_PROB;
    double MONITORING_PACKET_RECV_LOSS_PROB;
    int MAX_RETRIES;
    string clientAddress;
    int clientPort;
    atomic<int> requestIdCounter;
    SOCKADDR_IN serverAddress;
    SOCKET socketDescriptor;

public:
    Handler(int BUFFER_SIZE, double PACKET_SEND_LOSS_PROB, double PACKET_RECV_LOSS_PROB, double MONITORING_PACKET_RECV_LOSS_PROB, int MAX_RETRIES);

    string getClientAddress();
    string generateRequestId(string clientAddress, int clientPort);
    string receiveOverUDP(SOCKET socket, vector<char> marshalledData, bool status);
    string sendOverUDP(string requestContent);
    string monitorOverUDP();
    string GetWSAErrorMessage(int errorCode);
    bool isSocketOpen(SOCKET socket);
    double getRandomNumber();

    void connectToServer(string serverAddress, int serverPort);
    void openPort(int clientPort);
    void disconnect();
};

#endif