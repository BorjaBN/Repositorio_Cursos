# SEMANA 1


# Ejercicio 1.1: Instalación y configuración de sistemas operativos

Se trara del Ejercicio 1.1 del plan de formación dual con el que se pretence acercar al alumno al uso de los comando de shell de linux.

Aquí se muestran las siguientes tareas completadas:

- Asignar recursos adecuado.

- Crear usuarios.

- Configurar carpetas compartidas y acceso remoto.


## 1- Asignar recursos adecuados.

Una vez descargada la herramienta donde vamos a trabajar, *VirtualBox*, y una *iso de Linux sin GUI*, comenzamos con la ceración de las máquinas virtuales. 

![Creación de vm](/Ejercicio1.1/imagenes/imagen1.png)

En esta primera parte, VirtualBox nos pedirá dar un nombre a la máquina virtual (en futuras referencisa sera vm y el nombre de ésta cunado sea necesario), donde queremos guardar las vm dentro del host local (el ordenador físico) y le deberemos de indicar el path de donde se encuentra la iso a utilizar.

A continuación, comenzamos a asignarle realmente a la máquina los valores que deeseamos que tenga, siempre dentro de las capacidades del ordenador real que las mantiene.

![Aignación de valores](/Ejercicio1.1/imagenes/imagen2.png)

Como para la tarea que vamos hacer tampoco necesitamos nada que sobresalga mucho, se ha decidio darle 2 núcleos al procesador y 2 GB de memoria ram.

![Asignación de valores2](/Ejercicio1.1/imagenes/imagen3.png)

Para la memoria del disco duro se ha decidido darle 20 GB de memoria.


Y una vez instalado el sistema operativo nos saldrá una ventana en la que nos pedirá el nombre del usuario y la contraseña que pusimos anteriormente.

## 2- Creación de usuarios.

Para la creación de los diferentes usuarios que tendremos en la vm lanzaremos el comando **sudo adduser nombreUser**, utilizando "sudo" como administrador que somos (y pertenecemos al grupo sudousers) para decirle al sistema que tenemos permisos de ejecucción.

![Primer usuario](/Ejercicio1.1/imagenes/imagen4.png)

Un dato a mencionar, es que no se permiten nombres que conengan mayúsuclas, en caso de que queramos que lo tuvieran deberíamos de poner el nombre entre comillas simples. (P.e.: 'LuisProfe')

## 3- Configurar carpetas compartidas y acceso remoto.

Primero debemos de actualizar la maquina con el comando **sudo apt update**, continuamos descargando el servidor de ssh con **sudo apt install openssh-server**, una vez hecho todo esto debemos de cerrar las vm y cambiar el tipo de red.

![Cambio de la configuración](/Ejercicio1.1/imagenes/imagen5.png)

Desde el propio Virtualbox hacemos click derecho sobre la vm deseada y entramos en configuración.

![Cambio de red](/Ejercicio1.1/imagenes/imagen6.png)

Una vez dentro vamos al apartado de "Red", donde pone "*Conectado a:*" cambiamos de red "NAT" a "Red Interna". Estos pasos que se están describiendo, son necesarios de hacer en ambas máquinas.

Esto lo hacemos con intención de evitar los problemas que hemos tenido durante el curso con el plan de hacerlo a traves de un puerta de enlace, la cual no dura demasiado tiempo e imposibilitaba realizar la conexión.

Todo esto se debe de hacer después de hacer las actualizaciones y las descargas pertinentes dentro de cada vm, debido que al cambiar la red NAT, perdemos la conexión a internet.

Continuamos comprobando si el servidor ssh está activo con el comando **sudo systemctl status ssh**.

![Estado del ssh](/Ejercicio1.1/imagenes/imagen7.png)

Proseguimos lanzando el comando **ifconfig** para comprobar que las vm tienen un "enp0s3", el cual nos servirá para lanzar el siguiente comando con el que asignaremos una ip fija a cada una de las vm, esto lo hacemos con **ip addr add 192.168.1.10/24 dev enp0s3** (esta sería la ip de la mv maquina1) y **ip addr add 192.168.1.20/24 dev enp0s3** (de la vm paclonar).

![Asignando ip fija](/Ejercicio1.1/imagenes/imagen8.png)

Tras asignar la ip correspondiendte a cada maquina,  procedemos a realizar la conexión; al ser la primera vez que se conectan las vm entre sí, darán un warning avisando de que *"no sabe si la maquina a la que se está conectando es segura"*, nos pedirá confirmarle dos veces y listo, ya estariamos conectados. (Esto debemos de hacerlo en ambas máquinas)

![Mensaje de confirmación de conexión](/Ejercicio1.1/imagenes/imagen9.png)

Al tener el mismo usuario en ambas máquinas no es necesario cambiar el nombre de antes del arroba, solo debemos de cambiar el numero final de la ip. Hay que hacerlo en cada maquina.

Una vez conectados mediandte el ssh vamos a crear una carpeta y un archivo en una maquina desde la otra con **ssh nombreUser@nombreMaquina ‘mkdir -p ~/carpeta_compartida’**.

![Creación carpeta](/Ejercicio1.1/imagenes/imagen10.png)

A continuación, crearemos un archivo llamado archivo.txt dentro de esa carpeta con el comando **echo "mensaje" > nombreArchivo.txt** para añadirle así un mensje.


![Creación archivo](/Ejercicio1.1/imagenes/imagen11.png)

Ahora, procedemos a compartir tanto el archivo como la carpeta y acabamos comprobando. (Todo esto se ha relizado desde la vm paclonar con la idea de verlo desde la vm maquina1)

![Ultimos comandos de paclonar](/Ejercicio1.1/imagenes/imagen12.png)

![carpeta visible en vm maquina1](/Ejercicio1.1/imagenes/imagen13.png)

Tras lanzar todos los comandos anteriormente citados, acabamos comprobando en maquina1 que tenemos y podemos ver la carpeta creada y en su interior el archivo txt también compartido.


---

# Ejercicio 1.2: Gestión del sistema de archivos.

Se trata del Ejercicio 1.2 del plan de formación dual con el que tendremos como objetivo trabjar con los permisos, carpetas, enlaces simbólicos y estructuras jerárquicas en Linux.

Las tareas completadas son:

- Crear un árbol de directorios que simule departamentos de una empresa.


- Asignar permisos personalizados a grupos y usuarios.

## 1- Creación del árbol de directorios.

Comenzamos creando las carpetas dentro de nuestra máquina con **sudo mkdir nombreCarpeta**, nos pedirá una contraseña y luego creará la carpeta, para poder verla hacemos un **ls** y nos mostrará el contenido que tenemos dentro de donde estamos.

![Creación carpeta empresa](/Ejercicio1.2/imagenes/imagen1.png)

Lo que queremos hacer es crear más carpetas dentro de esta, por lo que si repetimos el mismo procedimiento anterior lo que haremos será crear carpetas al mismo nivel que la que ya hemos hecho, por lo que debemos de entrar dentro de esta y crearlas, esto lo hacemos con **cd nombreCarpetaEntrar**. (Si la carpeta a la que queremos entrar no se encuentra donde estamos, debemos de movernos a traves del path de los directorios como lo haríamos con un explorador de archivos)

![Entramos a carpeta empresa](/Ejercicio1.2/imagenes/imagen2.png)

Una vez dentro de la carpeta empresa, creamos las diferentes carpetas que queremos que imiten a los departamentos, podemos hacerlo de uno en uno con **sudo mkdir** o bien con **sudo mkdir -p {nombre,nombre2,nombre3}**, añadiendo el "-p" para poder hacer una tarea un poco más compleja, dentro de las llaves los nombres deben de estar separados por comas y sin espacios.

![Creación de carpetas dentro de empresa](/Ejercicio1.2/imagenes/imagen3.png)

Para poder ver como tenemos la distribución de las carpetas de una manera mas visual, utilizamos el comando **tree**, viéndose tal que así:

![Comando tree](/Ejercicio1.2/imagenes/imagen4.png)

## 2- Asignar permisos personalizados a grupos y usuarios.

Comenzamos creando grupos, a los que llamaremos de la misma forma que a las carpetas, para hacerlo más intuitivo a futuro cunado les asignemos los permisos. Utilizaremos el comando **sudo groupadd nombreGrupo**.

![Creación de grupos](/Ejercicio1.2/imagenes/imagen5.png)

A continuación vamos a crear los usuarios y asignarles un grupo con el comando **sudo useradd -m -G nombreGrupo nombreUser**.

![Creación de usuarios](/Ejercicio1.2/imagenes/imagen6.png)

Para los permisos vamos a hacer que en cada directorio esté asignado a un grupo con su mismo nombre, haciendo que solo tengan permisos las personas de dichos grupos. (Junto con root)

![Permisos a solo a grupos](/Ejercicio1.2/imagenes/imagen7.png)

Para hacer que otro usuario tenga permiso sobre el directorio, se ha decidido convertirlo en propietario, así se mantiene el grupo de finanzas, y ahora paco, como personas con permisos sobre ese directorio.

![Permisos a persona](/Ejercicio1.2/imagenes/imagen8.png)

Todo esto lo hemos hecho con los comandos **sudo chown paco /empresa/finanzas** y **sudo chmod 770 /empresa/finanzas**. Al final se muestra como paco sigue estando únicamente en el grupo de ventas.

---
# Ejercicio 1.3: Iniciación a Git


Se trata del Ejercicio 1.3 del plan de formación dual con el que se pretende familiriazar con Git y poder usarlo como herramienta de control de veriones. 

Este muestra las siguientes partes completadas:


- Inicializar un repositorio local y enlazarlo con GitHub.

- Realizar commits con mensajes claros.

- Crear ramas, fusionarlas y resolver conflictos simples Asignar recursos adecuados.

## 1- Inicializar un repositorio local.

Primero de todo, creamos en el escritorio de forma manual una carpeta a la hemos decidido llamar *"Repositorio_Cursos"*. Hacemos click sobre ella con el botón derecho y la abrimos con *git bash*, así se nos abrirá la consola de git con el path del repositorio directamente.

Lanzamos el comando **git init** para convertir la carpeta en un repositorio Git local; también nos crea dentro de este directorio una carpeta invisible llamada **.git**.

![comando git init](/Ejercicio1.3/imagenes/imagen1.png)

![ver contenido del repositorio](/Ejercicio1.3/imagenes/imagen2.png)

Con estas imagenes podemos ver que se ha creado el repositorio junto con su path y en la segunda imagen podemos ver como se ha creado la carpeta **.git** aunque no la veamos realmente al abrir la carpeta manualmente.

## 2- Enlazar el repositorio local con GitHub.

Nosotros al ya tener un repositorio remoto únicamente debemos de entrar en la pagina de GitHub y darle al botón de nuevo repositorio.

![Botón de nuevo repositorio](/Ejercicio1.3/imagenes/imagen3.png)

Nos salcrá esto mismo, donde le daremos nombre al reposirotio, lo haremos público o privado en función de lo que queramos y al haber hecho el paso anterior **no le debemos dar al botón de añadir un README**. (Tampoco necesitamos añadir un .gitignore o licencia)

Al bajar un poco más en la propia página aparece el botón de **crear reposirotio**, donde al pulsarlo nos mandarán aquí:

![comandos de github a seguir](/Ejercicio1.3/imagenes/imagen4.png)

Donde deberemos de copiar la ruta SSH que nos pone para copiar en la ventana azul. Ahora, pasamos de nuevo a la consola de git Bash y lanzaremos los comandos como nos indica en la misma página.

1.  **git remote add origin** + la ruta copiada.
2.  **git branch -m main**
3.  **git push -u origin main**

Así habremos enlazado el repositorio local con el remoto de GitHub.


## 3- Realización de commits claros.

Cada vez que hagamos un cambio en el repositorio y añadirlo con **git add** es conveniente realizar un commit con el comando **git commit -m "__"** donde le añadimos el "-m" para darnos la posibilidad de crear un mensaje que pondremos entre comillas, intentando que sean lo más descriptivos posibles, bien para un posible futuro en el que posiblemente no nos acordemos con que estabamos trabajando en ese momento o bien en un posible caso de que trabajemos con un equipo y el resto de personas sepan que hemos hecho.


![Un ejemplo de commit](/Ejercicio1.3/imagenes/imagen5.png)

Este sería un ejemplo de como se viese el commit.

Existe un comando que nos será muy util a futuro y para ver los commits, **git log** con este comando podemos ver un historico de los commits que se han hecho en el repositorio donde podremos ver:

- Los commits existentes junto con sus hash.
- Quién hizo el commit.
- La fecha de cuando se hizo el commit.
- Y también el mensaje que se puso al commit.

## 4- Crear ramas y fusionarlas.

Podemos crear ramas bien con el comando **git branch nombreRama** donde nos crea la rama pero nos mantenemos en la rama en la que estabamos inicialmente, o bien **git checkout -b nombreRama** con el que crearemos la rama nueva y nos moveremos a dicha rama.

Para poder movernos entre ramas podemos utilizar **git checkout nombreRama** que nos moverá de una a otra igual que **git switch nombreRama**.

Una vez creada la rama hay que llevarla al repositorio remoto con **git push --set-upstream origin nombreRama**, para poder hacer push y pull fácilmente siempre que queramos.

![visualizar ramas con SourceTree](/Ejercicio1.3/imagenes/imagen6.png)

Con la herramienta recomendada en los cursos del plan de formación se puede ver de una manera más vistosa el como se ha ido trabajando con las diferentes ramas, desde crearlas y añadirles diferentes archivos, hasta unirlas todas con main trayendo así sus archivos.

Para unir las diferentes ramas a una, primero debemos de encontrarnos en la rama a la que queremos traer la otra rama, una vez en la rama deseada lanzamos el comando **git merge nombreRama**, trayéndonos así su contenido a ésta.

Para que todo ocurra como en la foto anteriormente añadida, debemos cambiar un poco el comando, siendo: **git merge --no-ff nombreRama**, porque git utiliza un estilo por defecto que los cambios se ven todos en una sola línea, mientras que de la forma enseñada se ve de una manera más visual e intuitiva a simple vista.

## 5- Resolución de conflictos simples.

Con la intención de generar un conflicto para mostrar, se ha creado un fichero con **vim nombreFichero** al que hemos llamado fichero2, en el cual se ha escrito varias líneas y se ha commiteado correctamente, una vez hecho esto se ha procedido a borrar una línea de las escritas y se ha vuelto a commitear éste, continuamos repitiendo este último proceso y lo commiteamos por tercera vez. 

Ahora, vamos a revertir estos cambios con **git revert + 7 primeros difitos del hash del commit** (p.e.: git revert 4gh5j2m), debemos de añadirle el hash del commit al que queremos revertir, el cual veremos con **git log** como digimos anteriormente.

Esto nos ocasionará un problema, porque queremos volver a un contenido que actualmente está modificado, dándonos un error tal que así:

![Error al revertir](/Ejercicio1.3/imagenes/imagen7.png)

Esto nos viene a decir que existe un conflicto con el contenido del fichero y Git nos pide que hacer, desde eliminar el archivo, cancelar o continuar con el comando revert, nosotros queremos continuarlo por lo que vamos a entrar al archivo conflictivo y nos saldrá esto:

![Error dentro del fichero](/Ejercicio1.3/imagenes/imagen8.png)

Lo que esto nos quiere decir es que en la parte de "HEAD" (el último commit) lo que se encuentra en el archivo y desde el "=" que es lo que queremos meter al haber aplicado el revert, entonces somos nosotros quienes decidimos con qué nos quedamos borrando las marcas de conflicto (<<<<<<<, =======, >>>>>>>) y dejando lo deseado.

Continuamos con el revert y éste nos mostrará un mensaje al solucionar el conflicto donde podremos escribir como un commit:

![Solución de conflicto](/Ejercicio1.3/imagenes/imagen9.png)

Para poder escirbir deberemos de pulsar "i" de "insert" y para guardarlo pulsaremos "scape" y lo guardaremos con ":wq" (w para guardar y q para cerrar el archivo).


---

# Ejercicio 1.4: Primera base de datos.

Se trata del Ejercicio 1.4 del plan de formación dual, con el que se pretende acercarnos MySQL y las Bases de Datos.

Las tareas que se han completado son:

- Crear tablas "clientes" y "productos".


- Insertar registros simulados.

- Ejecutar consultas SELECT, JOIN y filtros básicos Asignar recursos adecuados


Como no se ha especificado me he tomado la libertad de hacer uso de MySQL WorkBench, debido a que ya tenia hecha su instalación y su manejo y uso me resultan más cómodos.

## 1- Creación de tablas clientes y productos.

Comenzamos lanzando un comando **CREATE DATABASE nombreBBDD**.

![Creación BBDD](/Ejercicio1.4/imagenes/imagen1.png)

Al hacer uso del comando **USE nombreBBDD**, nos mete directamente dentro de la propia base de datos, para trabjar directamente sobre ella.

![Creaciónd de tablas](/Ejercicio1.4/imagenes/imagen2.png)

Continuamos creando las tablas pedidas directamente con los comandos **CREATE TABLE nombreTabla**, a los cuales les hemos añadidos algunos valores de datos para poder realizar consultas más tarde.


## 2- Insertar registros simulados


![Inserción de datos](/Ejercicio1.4/imagenes/imagen3.png)


Añadimos los datos a las tablas con el comando **INSERT INTO nombreTabla**. Ya tenemos las tablas totalmente operativas, podemos comenzar ha realizar algunas operaciones sencillas.


## 3- Ejecución de consultas.

Vamos a comenzar a realizar algunas consultas sencillas, como son los **SELECT**.

![1º SELECT](/Ejercicio1.4/imagenes/imagen4.png)

Con este comando estamos pidiendo a la base de datos que nos muestre todos los productos de la tabla productos.


![2º SELECT](/Ejercicio1.4/imagenes/imagen5.png)


Con este comando estamos pidiendo a la base de datos que nos muestre todos los datos de la tabla clientes cuando coincidan en el parámetro de que ciudad sea igual a 'Madrid'.


![Creación de Tablaventas](/Ejercicio1.4/imagenes/imagen6.png)


Para poder hacer **JOIN** me he tomado la libertad de crear una tercera tabla que tubiese datos comunes de las dos anteriores.


![1º JOIN](/Ejercicio1.4/imagenes/imagen7.png)


Con este JOIN conseguimos mostrar los clientes que han comprado, el producto que compraron, la cantidad y la fecha.


![2º JOIN](/Ejercicio1.4/imagenes/imagen8.png)


Con esto mostramos el id de la venta y el nombre del producto.


---


# SEMANA 2


## Ejercico 2.1: Modelado realcional.


Se trata del ejercico 2.1, donde se nos da un enunciado para abordar tanto este ejercico como el siguiente. Éste es:


*Enunciado: Una biblioteca necesita gestionar libros, autores y préstamos. Los libros tienen título, ISBN y categoría. Los autores tienen nombre y nacionalidad. Un libro puede tener varios autores. Los préstamos registran qué libro se presta a qué usuario y en qué fecha*


Donde las tareas completadas son:


- Crear diagrama de Entidad - Relación.


- Crear tablas resultantes normalizadas.


## 1- Creación del diagrama ER.


En función de los datos dados por el enunciado se ha realizado el diagrama ER de la misma forma que lo hemos ido haciendo en el periodo de clases. 

![Diagrama ER](/Ejercicio2.1/imagenes/imagen1.png)


En este diagrama podemos ver las siguientes entidades y relaciones:


Entidades:

- **Autores** ( IdAutor(PK), Nombre_Autor, Nacionalidad )


- **Libros** ( ISABN(PK), Título, Categoría )


- **Préstamo** ( IdPréstamo(PK), Fecha_Inc, Fecha_Fin  )


- **Usuarios** ( IdUsuario(PK), Nombre_Usuario, Teléfono )


Entre los paréntesis indicamos los atributos de estas entidades.


Relacions:

- **Escribe**


- **Contiene**


- **Realiza**


Debido a que **préstamo** es una *entidad débil* de existencia, porque un préstamo no existe sin libro ni sin los usuarios, hace que las relaciones en las que interviene esta entidad, sean también "débiles".


De este diagrama entendemos que un **libro** puede estar escrito por varios **autores**, a la vez que un **autor** puede escribir varios **libros**. También, un **libro** puede estar en un **préstamo** o no, pero en el caso de que esté, solo puede prestarse una vez de golpe; mientras que un **préstamo** puede ser de varios libros diferentes. Con respecto al **usuario**, este puede realizar varios **prestamos** en el tiempo (uno hoy, otro mañana, otro pasado, etc.) y un **préstamo** solo puede ser hecho por **usuario** a la vez. (P.e. un usuario hace un prestamo de varios libros y otro de uno solo, ambos estan haciendo un único prestamo de diferentes cantidades de libros.)


## 2- Creación de las tablas normalizadas.

Tras normalizar hasta la 3º forma normal, obtenemos las siguientes tablas con sus diferentes atributos:


![Tablas normalizadas](/Ejercicio2.1/imagenes/imagen2.png)


Se ha creado la tabla Libros_Autores para resolver la relación "**N:M**" entre ambas tablas

En la siguiente actividad veremos como quedan estas tablas ya implementadas en la base de datos con la que trabajaremos.


# Ejercicio 2.2: Creación de base de datos.


Se trata del ejercicio 2.2 donde vamos a crear la base de datos en función de lod datos del ejercicio 2.1 y su enunciado. 
Como en ejercicos anteriores, vamos a utiliar la herramienta MySQLWorckbench para crear y trabajar con la base de datos.


Para esta tarea se han completado las siguientes partes:


- Crear las tablas y relaciones.


- Insertar 5 libros, 3 autores y 4 préstamos.


- Probar consultas con JOIN, ORDER BY y filtros.


## 1- Creación de tablas.


Procedemos a crear la base de datos de la misma manera que lo hicimos en el ejercicio 1.4 (con el comando CREATE DATABASE + nombre) y continuamos creando y añadiendo las tablas de las diferentes entidades y relaciones.

![SQL creación tablas1](/Ejercicio2.2/imagenes/imagen1.png)
![SQL creación tabals2](/Ejercicio2.2/imagenes/imagen2.png)


Se ha decicido poner el atributo "Telefono" como VARCHAR para habilitar la posibilidad de que a la hora de ingresar los números, estos puedan estar separados por "-" o por un espacio.


Otras decisiones que se han tomado para hacer más real la creación de las tablas han sido el hacer que los "**Id**" dean incrementados automáticamente, se ha impedido la posibilidad de borrar un **libro** de la base de datos siempre y cuando éste esté en añadido también en **prestamos** (*DELETE RESTRICT*); también, se ha añadido la opcion de que si el **ISBN** o el **IdAutor** son modificados en la tabla de origen, estos datos se actualicen también en el resto de tablas que aparezcan (*UPDATE CASCADE*).


## 2- Inserción de datos.


Comnezamos a insertar datos dentro de sus respectivas tablas:

![Autores y libros](/Ejercicio2.2/imagenes/imagen3.png)


Con el comando **INSERT INTO + nombreTabla** insertamso los datos, primero lanzamos los autores y los libros para que se genere el Id correspondiente. 


![Tabla relación](/Ejercicio2.2/imagenes/imagen5.png)


Aquí emparejamos los libros con los autores, por lo que el Id debía de estar creado de antes, como lo pusimos de autoincremento debíamos lanzar primero los comandos anteriores en lugar de a la vez con este último.


![Datos users](/Ejercicio2.2/imagenes/imagen4.png)


Ahora, insertamos los datos de los usuarios, como no especificaba cuantos, me he tomado la libertad de poner uno más que los préstamos a realizar.


![Préstamos registrados](/Ejercicio2.2/imagenes/imagen6.png)


Lanzamos los comando y obtenemos lso préstamos realizados, indicando el ISBN, el IdUsuario y la fecha de inicio del préstamo.


## 3- Pruebas de consultas JOIN, ORDER BY y FILTROS


Procedemos a realizar una serie de consultas:


![JOIN y ORDER BY 1](/Ejercicio2.2/imagenes/imagen7.png)


Con esta consulta obtenemos la lista completa de todos los libros que tenemos en la biblioteca, y para cada libro, mostramos quiénes son sus autores y cuál es su nacionalidad; ordenados por orden alfabético los títulos de estos.


![JOIN y ORDER BY 2](/Ejercicio2.2/imagenes/imagen8.png)


Con esta consulta obtenemos todos los registros de los prestamos de forma detallada, mostrando su Id, el título del libro, el ISBN de este, el usuario que ha realizado el prestamo junto con su nº de teléfono y la fecha en que lo realizó. Se ordena en función de la fecha con un orden descendente (el más nuevo arriba).


---


Vamos a continuar con algunas consultas sencillas que aporten de ejemplo para los filtros.

![Filtros 1](/Ejercicio2.2/imagenes/imagen10.png)


En este ejemplo combinamos el uso de **JOIN**, como de **ORDER BY** y también de los filtros con **WHERE**. 


Filtramos los libros por el nombre del autor y mostramos todos aquellos que coincidan con que el nombre de este sea **'Tolkien'**.


![Filtros 2](/Ejercicio2.2/imagenes/imagen9.png)


Con esta consulta obtenemos una lista de todos los autores de los que tenemos registrados en al BBDD que tengan  nacionalidad 'Británica'


# Ejercicio 2.3: Aplicación de Java con conexión a Base de Datos


Se trata del ejercicio 2.3 donde vamos a crear con la ayuda de *Intellij* una alicación que haga uso de la base de datos que hemos creado anteriormente (**Biblioteca**).


Se han completado las siguientes tareas:


- Usar JDBC para conectar con la BD.


- Crear clases Libro, Usuario y Prestamo.


- Validar entradas, capturar excepciones y mostrar mensajes de error claros.


## 1- Uso de JDBC para conectar con la BBDD.


Para el uso de JDBC es conveniente actualizar los drivers modificando el POM de nuestro proyecto, debido a que es un método más seguro en comparación con la descarga e instalación de algún tipo de archivo como un .jar.


Por ello, debemos de buscar en la pagina web de **MAVEN Repository** un versión que nos convezca,. (Pagina web utilizada en el periodo ordinario de clases)

Nosotros utilizaremos esta verisón:


[MySQL conector/J](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/8.0.33)


![Depencencias POM](/Ejercicio2.3/imagenes/imagen1.png)


Dentro del archivo POM buscamos un apartado llamado **dependencies** y pegamos dentro de este el texto copiado del enlace anterior, de esta manera se nos crearán las dependencias necesarias para poder hacer uso de JDBC de una manera segura. 


Ahora solo nos faltaría actualizar el archivo pulsando encima del boton con forma de *M* para ejecutar el POM.


## 2- Creación de las clases a utilizar.


Con el uso de *Intellij* se ha creado un nuevo proyecto dentro del repositorio del plan de formación, para de esta manera entregar una versión más limpia los trabajos solicitados. 


![Libro1](/Ejercicio2.3/imagenes/imagen2.png)
![Libro2](/Ejercicio2.3/imagenes/imagen3.png)
![Libro3](/Ejercicio2.3/imagenes/imagen4.png)


Esta sería el codigo de la clase Libro


![User1](/Ejercicio2.3/imagenes/imagen5.png)
![User2](/Ejercicio2.3/imagenes/imagen6.png)
![User3](/Ejercicio2.3/imagenes/imagen7.png)


El código de la clase Usuario


![Prestamo1](/Ejercicio2.3/imagenes/imagen8.png)
![Prestamo2](/Ejercicio2.3/imagenes/imagen9.png)
![Prestamo3](/Ejercicio2.3/imagenes/imagen10.png)


Este sería el código de la clase Prestamo


## 3- Validación de entradas, captura de excepciones y mensajes de errores.


Comenzamos lanzando la aplicación llamada **"AplicacionBiblioteca"**, cuando lo hagamos nos mostrará el menú con unas instrucciones sencillas, pero vamos a generarle algnuos fallos para esta prática.


![Entrada no valida](/Ejercicio2.3/imagenes/imagen11.png)


Al no introducir un número que la aplicación estaba esperando, nos muestra un mensaje de error.


![ISBN vacío](/Ejercicio2.3/imagenes/imagen12.png)


A la hora de registrar un nuevo préstamo, el programa no nos deja dejar en blanco los datos relevantes, nos mostrará un mensaje diciendo que no es posible esto.

De la misma forma, si introducimos un dato que no sea correcto nos avisará también con un mensaje de ello.


![Error de ISBN](/Ejercicio2.3/imagenes/imagen13.png)


Una vez hayamos realizado todo con exito, nos mostrara un mensaje de que está todo correcto, como vemos aquí:


![Exito en el préstamo](/Ejercicio2.3/imagenes/imagen14.png)



![Ecepciones1](/Ejercicio2.3/imagenes/imagen15.png)
![Ecepciones2](/Ejercicio2.3/imagenes/imagen16.png)


Esta seria la captura de las excepciones que se ha utilizado.


---