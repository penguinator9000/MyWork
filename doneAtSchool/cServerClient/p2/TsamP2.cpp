#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <ctype.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <vector>

using namespace std;

bool check(int low_port, int high_port)
{
        if (low_port < high_port)
        {
                return true;
        }
        else if (low_port > high_port)
        {
                return false;
        }
        return false;
}

long int change_to_int(const char* port)
{
        if (isdigit(*port))
        {
                char *validator;
                long int true_port = strtol(port, &validator, 10);

                if (validator[0] != 0)
                {
                        printf("Ports are not decimal\n");
                        return -1;
                }

                return true_port;
        }
        return -1;
}

vector <int> return_open_ports(char const* ip_addr, int low_port, int high_port)
{
        vector<int> open_ports;
        int sockfd;
        struct sockaddr_in server_addr;
        socklen_t some;
        struct sockaddr dest_addr;
        char buffer[1024];
        int buff_length;

        //char const* destip = "172.28.22.6";

        strcpy(buffer, "Somebody");
        buff_length = strlen(buffer)+1;

        if ((sockfd = socket(AF_INET, SOCK_DGRAM, 0))<0)
        {
                return open_ports;
        }

        memset(&server_addr, 0, sizeof(server_addr));

        server_addr.sin_family = AF_INET;
        inet_aton(ip_addr, &server_addr.sin_addr);

        struct timeval timeout;
        timeout.tv_sec = 0;
        timeout.tv_usec = 50000;

        if (setsockopt(sockfd, SOL_SOCKET, SO_RCVTIMEO, (char *)&timeout, sizeof(timeout)))
        {
                printf("Could not\n");
        }

        char buff2[1024];
        int buff2_len = 1024;

        while (low_port != high_port+1)
        {
                server_addr.sin_port = htons(low_port);

                //printf("sending %d\n", low_port);
                if (sendto(sockfd, buffer, buff_length, 0, (const struct sockaddr *) &server_addr, sizeof(server_addr))!= SO_ERROR)
                {
                        //printf("sent %d\n", low_port);

                        if (recvfrom(sockfd, buff2, buff2_len, 0, (struct sockaddr *) &server_addr, socklen_t &server_addr) < 0)
                        {
                                printf("could not connect\n");
                        }
                        else
                        {
                open_ports.push_back(low_port);
                        }
                }

                low_port++;
        }

        close(sockfd);
        return open_ports;
}

int main(int argc, char const* argv[])
{
        long int low_port;
        long int high_port;
        std::string ip_addr;
        vector<int> open_ports;

        if (argc != 4){
                printf("\nIncorrect format of input");
                return -1;
        }

        low_port = change_to_int(argv[2]);
        high_port = change_to_int(argv[3]);

        if ((low_port<0) | (high_port<0))
        {
                printf("\nIncorrect format of ports");
                return -1;
        }

        open_ports = return_open_ports(argv[1], low_port, high_port);
        auto i = open_ports.cbegin();

        while (i != open_ports.cend())
        {
                cout << *i << endl;
                i++;
        }
        return 0;
}