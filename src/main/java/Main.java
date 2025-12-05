import java.util.Scanner;

import model.Categorias;
import model.Cursos;
import model.Estudiantes;
import repository.CategoriasRepository;
import repository.CursosRepository;
import repository.EstudiantesRepository;

public class Main{
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        CategoriasRepository categoriasRepository = new CategoriasRepository();
        
        System.out.println("Ingrese el nombre de la categoría: ");
        String nomCategoria = scan.nextLine();

        if (nomCategoria.isEmpty()) {
            System.out.println("No se puede crear una categoría sin nombre.");
            return;
        }

        Categorias categoria = new Categorias(nomCategoria);
        categoriasRepository.insertarCategoria(categoria);

        scan.nextLine();

        /* Listar Categorías */
        System.out.println("Lista de Categorías:");
        for (Categorias c : categoriasRepository.listarCategorias()) {
            System.out.println("Id : " + c.getIdCategoria() + "\nNombre : " + c.getNomCategoria());
        }

        scan.nextLine();

        /* Buscar Categoría */
        System.out.print("Ingrese el Id de la Categoría a buscar: ");
        Integer idBuscado = scan.nextInt();
        Categorias idEncontrado = categoriasRepository.listCategorias(idBuscado);

        if (idEncontrado != null) {
            System.out.println("Categoría encontrada:\nId : " + idEncontrado.getIdCategoria() +
                               "\nNombre : " + idEncontrado.getNomCategoria());
        }

        /* Actualizar Categoría */
        System.out.print("Ingrese el Id de la Categoría que quiere editar: ");
        int idEditar = scan.nextInt();
        scan.nextLine();

        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = scan.nextLine();

        Categorias categoriaActualizada = categoriasRepository.actualizarCategorias(idEditar, nuevoNombre);

        if (categoriaActualizada != null) {
            System.out.println("Categoría actualizada:");
            System.out.println("Id: " + categoriaActualizada.getIdCategoria());
            System.out.println("Nombre: " + categoriaActualizada.getNomCategoria());
        }

        /* Eliminar Categoría */
        System.out.print("Ingrese el Id de la Categoría que desea eliminar: ");
        int idEliminar = scan.nextInt();

        int filas = categoriasRepository.eliminarCategorias(idEliminar);
        if (filas > 0) {
            System.out.println("Categoría eliminada correctamente.");
        }


        scan.nextLine();

        CursosRepository cursosRepository = new CursosRepository();
        
        System.out.println("Ingrese el Título del Curso: ");
        String titulo = scan.nextLine();

        System.out.println("Ingrese la descripción del Curso: ");
        String descripcion = scan.nextLine();

        System.out.println("Ingrese el ID de la Categoría del Curso: ");
        Integer idCategoria = scan.nextInt();

        if (idCategoria <= 0) {
            System.out.println("No se encuentra la categoría :(");
            return;
        }

        Cursos cursos = new Cursos(titulo, descripcion, idCategoria);
        cursosRepository.insertarCurso(cursos);

        scan.nextLine();

        /* Listar Cursos */
        System.out.println("Lista de Cursos:");
        for (Cursos cu : cursosRepository.listarCursos()) {
            System.out.println(
                "Id : " + cu.getIdCurso() +
                "\nTítulo : " + cu.getTitulo() +
                "\nDescripción : " + cu.getDescripcion() +
                "\nId Categoría : " + cu.getFkidCategoria()
            );
        }

        scan.nextLine();

        /* Buscar Curso */
        System.out.print("Ingrese el Id del Curso a buscar: ");
        Integer idBuscadoCurso = scan.nextInt();
        Cursos cursoEncontrado = cursosRepository.listCursos(idBuscadoCurso);

        if (cursoEncontrado != null) {
            System.out.println("Curso encontrado:\nId : " + cursoEncontrado.getIdCurso() +
                               "\nTítulo : " + cursoEncontrado.getTitulo() +
                               "\nDescripción : " + cursoEncontrado.getDescripcion() +
                               "\nId Categoría : " + cursoEncontrado.getFkidCategoria());
        }

        /* Actualizar Curso */
        System.out.print("Ingrese el Id del Curso que quiere editar: ");
        int idEditarCurso = scan.nextInt();
        scan.nextLine();

        System.out.print("Ingrese el nuevo título: ");
        String nuevoTitulo = scan.nextLine();

        System.out.print("Ingrese la nueva descripción: ");
        String nuevaDescripcion = scan.nextLine();

        System.out.print("Ingrese el nuevo ID de Categoría: ");
        Integer fkIdCategoriaNuevo = scan.nextInt();

        Cursos cursoActualizado = cursosRepository.actualizarCursos(idEditarCurso, nuevoTitulo, nuevaDescripcion, fkIdCategoriaNuevo);

        if (cursoActualizado != null) {
            System.out.println("Curso actualizado:");
            System.out.println("Id: " + cursoActualizado.getIdCurso());
            System.out.println("Título: " + cursoActualizado.getTitulo());
            System.out.println("Descripción: " + cursoActualizado.getDescripcion());
            System.out.println("Id Categoría: " + cursoActualizado.getFkidCategoria());
        }

        /* Eliminar Curso */
        System.out.print("Ingrese el Id del Curso que desea eliminar: ");
        int idEliminarCurso = scan.nextInt();

        int filasCurso = cursosRepository.eliminarCursos(idEliminarCurso);

        if (filasCurso > 0) {
            System.out.println("Curso eliminado correctamente.");
        }

        scan.nextLine();


        EstudiantesRepository estudiantesRepository = new EstudiantesRepository();

        System.out.println("estudiante");

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombreEst = scan.nextLine();

        System.out.print("Ingrese el apellido del estudiante: ");
        String apellidoEst = scan.nextLine();

        System.out.print("Ingrese el email del estudiante: ");
        String emailEst = scan.nextLine();

        System.out.print("Ingrese el ID del curso al que pertenece: ");
        int idCursoEst = scan.nextInt();

        Estudiantes estudiante = new Estudiantes(nombreEst, apellidoEst, emailEst, idCursoEst);
        estudiantesRepository.insertarEstudiantes(estudiante);

        scan.nextLine();

        /* Listar Estudiantes */
        System.out.println("\n=== LISTA DE ESTUDIANTES ===");
        for (Estudiantes es : estudiantesRepository.listarEstudiantes()) {
            System.out.println(
                "Id: " + es.getIdEstudiante() +
                "\nNombre: " + es.getNombre() +
                "\nApellido: " + es.getApellido() +
                "\nEmail: " + es.getEmail() +
                "\nId Curso: " + es.getFkIdCurso()
            );
        }

        scan.nextLine();

        /* Buscar Estudiante */
        System.out.print("Ingrese el Id del estudiante a buscar: ");
        Integer idBuscarEst = scan.nextInt();
        Estudiantes estEncontrado = estudiantesRepository.listEstudiantes(idBuscarEst);

        if (estEncontrado != null) {
            System.out.println(
                "Estudiante encontrado:\nId: " + estEncontrado.getIdEstudiante() +
                "\nNombre: " + estEncontrado.getNombre() +
                "\nApellido: " + estEncontrado.getApellido() +
                "\nEmail: " + estEncontrado.getEmail() +
                "\nId Curso: " + estEncontrado.getFkIdCurso()
            );
        }

        /* Actualizar Estudiante */
        System.out.print("Ingrese el Id del estudiante a editar: ");
        int idEditarEst = scan.nextInt();
        scan.nextLine();

        System.out.print("Nuevo nombre: ");
        String nuevoNomEst = scan.nextLine();

        System.out.print("Nuevo apellido: ");
        String nuevoApeEst = scan.nextLine();

        System.out.print("Nuevo email: ");
        String nuevoEmailEst = scan.nextLine();

        System.out.print("Nuevo ID de Curso: ");
        int nuevoCursoEst = scan.nextInt();

        Estudiantes estActualizado = estudiantesRepository.actualizarEstudiante(
            idEditarEst, nuevoNomEst, nuevoApeEst, nuevoEmailEst, nuevoCursoEst
        );

        if (estActualizado != null) {
            System.out.println("Estudiante actualizado.");
        }

        /* Eliminar Estudiante */
        System.out.print("Ingrese el Id del estudiante a eliminar: ");
        int idEliminarEst = scan.nextInt();

        int filasEst = estudiantesRepository.eliminarEstudiantes(idEliminarEst);

        if (filasEst > 0) {
            System.out.println("Estudiante eliminado correctamente.");
        }

        scan.close();
    }
}