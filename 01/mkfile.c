/* authors: Jakob Ledig & Florian Nehmer */
/* Praktikum Betriebssysteme Sommersemester 2017 */
/* Praktiumsgruppe 4 */
/* Aufgabe 1 */

#include <stdio.h>
#include <string.h>

int main (int argc, char *argv[]){
  char input[30];
  
  if (argc == 2){
    printf("argument was: %s\n", argv[1]);
    strcpy(input, argv[1]);
    if (strlen(argv[1]) > 30) {
      printf("Argument too long!");
    }
  }
  else {
    scanf("Enter the file name: %s", input);
    
  }
  return 0;
}
