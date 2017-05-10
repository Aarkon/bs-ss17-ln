/*
 * A C-Program to implement/mimic a simple shell.
 *
 * Jakob Ledig & Florian Nehmer, 3.5.2017
 * Praktikum Betriebssysteme SS2017 Gruppe 4
 *
 * Best compile using the following command sequence:
 * cd bs-ss17-ln/02/hawsh
 * gcc -g -Wall -Wextra -pedantic-errors hawsh.c -o build/hawsh
 */


#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
#include <stdlib.h>


/**
 * Print the Prompt String:
 * "usint read_command(char);ername@cwd >"
 *
 */
void type_prompt() {
    char cwd[256];
    getcwd(cwd, sizeof(cwd));
    printf("%s@%s > ", getenv("USERNAME"), cwd);
}


/**
 *
 * @param command
 * @return
 */
int read_command(char *command) {
    fgets(command, 128, stdin);
    command[strlen(command) - 1] = '\0';
    if (command[strlen(command) - 1] == '&') {
        command[strlen(command) - 1] = '\0';
        return 1;
    } else {
        return 0;
    }
}



/**
 * Main function
 * @param argc
 * @param argv
 * @return
 */
int main(int argc, char **argv[]) {
    int PIDstatus;
    int command_in_background = 0;
    char command[128];

    while (true) {
        type_prompt();
        read_command(&command);

        if (strcmp(command, "help") == 0) {
            printf("Execute help function\n");
        } else if (strcmp(command, "version") == 0) {
            printf("Execute version function\n");
        } else if (strcmp(command, "quit") == 0) {
            exit(EXIT_SUCCESS);
        } else if (strncmp(command, "/", 1) == 0) {
            printf("Execute cd function\n");
        } else {
            printf("Execute execlp and check if run in background");
        }
    }
}