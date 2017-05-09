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
#include <stdbool.h>
#include <stdlib.h>
#include <unistd.h>


/* ..:: FUNCTION DECLARATIONS ::.. */
void shell_loop();

void type_prompt();

void read_command(char, char);

/* ..:: MAIN ::.. */
int main(int argc, char **argv[]) {
    while (true) {
        type_prompt();
//        read_command(&command, &params);
//
//        PIDstatus = fork();
//        if (PIDstatus < 0) {
//            printf("Unable to fork");
//            continue;
//        }
//        if (PIDstatus > 0) {
//            waitpid(PIDstatus, &status, 0);
//        } else {
//            execve(command, params, 0);
//        }
    }
}


/**
 * Print the Prompt String:
 * "username@cwd >"
 *
 */
void type_prompt() {
        printf("%s@", getenv("USERNAME"));
        printf("%s", getenv("PWD"));
        printf(" > \n"); // Leerzeile entfernen!
        sleep(2);

}


//void read_command(*char command, *char params)
//{
//
//}

