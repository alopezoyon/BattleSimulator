BattleSimulator 
===

**Un simulador de batalla para profundizar en la propgramacion modular y orientacion a objetos**

### Especificación del juego 

Battle Simulator es un juego por consola en el cual dos equipos se enfrentan en una batalla de estilo rpg por rondas. Este juego consta de varios modos de juego: Un modo de juego automático en el que el jugador es un espectador y observa el transcurso de la contienda, y un modo de juego en el que el usuario es el encargado de tomar las decisiones en cada turno así como de crear los equipos. Además, también se implementará la opción de cargar los equipos de la batalla desde un fichero externo y la posibilidad de guardar los equipos actuales en dicho fichero o guardar el desarrollo del juego en otro fichero de texto.

**Modo espectador**
---
En el modo espectador, los equipos contendientes son generados automáticamente de forma aleatoria considerando una serie de parámetros límite (no puede existir un equipo con 200 integrantes o un jugador con 100 de vida por ejemplo). Además, una vez generados los equipos, estos también se enfrentan de forma automática siguiendo la siguiente lógica:

1. Primero se comprueba que tanto el equipo atacante como el equipo atacado tenga al menos un miembro que no esté debilitado. En caso contrario se pondrá fin a la batalla con el correspondiente ganador.
2. En segundo lugar se elige de forma aleatoria la acción a realizar: curar a un jugador del mismo equipo o atacar a un jugador del equipo contrario.
3. En tercer lugar se realiza la acción:
  - En el caso de curar se busca un jugador aleatorio del mismo equipo que no esté debilitado
  - En el caso de atacar se busca el primer jugador más cercano a la posición de ataque que no esté debilitado. La posición de ataque es un contador que se incrementa cada vez que un jugador ataca a otro del equipo contrario (si la posición de ataque es superior al número de integrantes del equipo contrario, su valor vuelve a 0)
  
4. Si durante el proceso todos los integrantes de algún equipo se debilitan, la victoria será para el equipo contrario.
5. Si ya se han sucedido el número máximo de rondas, la victoria será para el equipo con más integrantes en pie. Si el número es el mismo se dará un empate
6. Si un jugador que no tiene objetos de curación cura a otro jugador, lo hace con 1 punto de curación (“hierbajo”)
7. Si un jugador ataca con 5 puntos de ataque a otro jugador que tiene 20 puntos de defensa, el atacado recibirá 1 punto de daño.

**Modo interactivo**
---
En el modo interactivo es el usuario el encargado de tomar las decisiones en todo momento, tanto la generación de equipos como el número de rondas y las acciones a realizar en cada ronda. Este modo de juego es más similar al de un rpg clásico de 2 jugadores (cada uno controlando un equipo diferente) o de 1 jugador (controlando los dos equipos). El concepto de este modo de juego es similar al anterior pero con la introducción de decisiones por teclado.


