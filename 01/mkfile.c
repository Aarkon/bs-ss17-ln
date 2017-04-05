/*
 * A C-program that takes an optional parameter or reads from user input
 * to generate an empty file with the given name.
 *
 * Jakob Ledig & Florian Nehmer, 6.4.2017
 * Praktikum Betriebssysteme SS2017 Gruppe 4
 *
 * Best compile using the following command:
 * gcc -g -Wall -Wextra -pedantic-errors mkfile.c -o build/mkfile
 */

#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <zconf.h>


int main(int argc, char *argv[]) {
    char input[30]; // any input longer than 30 characters will be truncated.
    int fd; // filedescriptor, takes the return code of the creat-statement

    // read an optional command line parameter:
    if (argc == 2) {
        strcpy(input, argv[1]);
        printf("Name der neuen Datei: %s\n", input);
    } else {
        printf("Name der neuen Datei: ");
        fgets(input, 31, stdin);
        // remove the newline at the end of input:
        input[strlen(input) - 1] = '\0';
    }

    // taken from creat's manpage:
    mode_t mode = S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH;
    //TODO: implement 0700-user access
    fd = creat(input, mode);
    //TODO: implement error message
    printf("Die Datei %s wurde erfolgreich angelegt!\n", input);
    close(fd);
    return fd;
}
