import java.util.Scanner;

import model.Categorias;
import model.Cursos;
import repository.CategoriasRepository;
import repository.CursosRepository;

public class Main{
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CategoriasRepository CategoriasRepository = new CategoriasRepository();
        
        System.out.println("Ingrese el nombre de la categoria: ");
        String nomCategoria = scan.nextLine();

        if (nomCategoria.isEmpty()) {
                System.out.println("No se puede crear una categoría sin nombre.");
            return;
        }

        Categorias categoria = new Categorias(nomCategoria);

        CategoriasRepository.insertarCategoria(categoria);

        scan.nextLine();

        /*Listar Categorias */
        System.out.println("Lista de categorias");


        for (Categorias categorias : CategoriasRepository.listarCategorias()) {
            System.out.println("Id : " + categorias.getIdCategoria() + "\n" + " Nombre : " + categorias.getNomCategoria());
        }

        scan.nextLine();

        /* Buscar Categoria por ID */
        System.out.print("Ingrese el Id de la Categoria a buscar: ");
        Integer idBuscado = scan.nextInt();
        Categorias idEncontrado = CategoriasRepository.listCategorias(idBuscado);

        if (idEncontrado != null) {
            System.out.println("Categoria encontrada: " + "\n" + "Id : " + idEncontrado.getIdCategoria() + "\n" + " Nombre : " + idEncontrado.getNomCategoria());
        }

        /* Actualizar Categoria */
        System.out.print("Ingrese el Id de la Categoria que quiere editar: ");
        int idEditar = scan.nextInt();
        
        scan.nextLine(); 

        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = scan.nextLine();

        Categorias categoriaActualizada= CategoriasRepository.actualizarCategorias(idEditar, nuevoNombre);

        if (categoriaActualizada != null) {
            System.out.println(" Categoria actualizado:");
            System.out.println("Id: " + categoriaActualizada.getIdCategoria());
            System.out.println("Nombre: " + categoriaActualizada.getNomCategoria());
        } else {
            System.out.println("No se pudo actualizar la Categoria :(");
        }

        /* Eliminar Categoria */

        System.out.print("Ingrese el Id de la Categoria que desea eliminar: ");
        int idEliminar = scan.nextInt();

        int filas = CategoriasRepository.eliminarCategorias(idEliminar);

        if (filas > 0) {
            System.out.println("Categoria eliminado correctamente.");
        } else {
            System.out.println("No se encontró la categoria o no se pudo eliminar :(");
        }

        scan.nextLine();

        /* CURSOS */

        CursosRepository cursosRepository = new CursosRepository();
        
        System.out.println("Ingrese el Titulo del Curso: ");
        String titulo = scan.nextLine();

        System.out.println("Ingrese la descripcion del Curso: ");
        String descripcion = scan.nextLine();

        System.out.println("Ingrese el ID de la Categoria del Curso: ");
        Integer idCategoria = scan.nextInt();


        if (idCategoria <= 0) {
                System.out.println("No se encuentra la categoria :(");
            return;
        }

        Cursos cursos = new Cursos(titulo, descripcion, idCategoria);
        
        cursosRepository.insertarCurso(cursos);

        scan.nextLine();

        /*Listar Cursos */
        System.out.println("Lista de Cursos");


        for (Cursos Cursos : cursosRepository.listarCursos()) {
            System.out.println("Id : " + Cursos.getIdCurso() + "\n" + " Titulo : " + Cursos.getTitulo() + "\n" + " Descripción : " + Cursos.getDescripcion() + "\n" + " Id Categoría : " + Cursos.getFkidCategoria());
        }

        scan.nextLine();

        /* Buscar cursos por ID */
        System.out.print("Ingrese el Id del Curso a buscar: ");
        Integer idBuscado1 = scan.nextInt();
        Cursos idEncontrado1 = cursosRepository.listCursos(idBuscado1);

        if (idEncontrado != null) {
            System.out.println("curso encontrado: " + "\n" + "Id : " + idEncontrado1.getIdCurso() + "\n" + " Titulo : " + idEncontrado1.getTitulo() + "\n" + " Descripción : " + idEncontrado1.getDescripcion() + "\n" + " Id Categoría : " + idEncontrado1.getFkidCategoria());
        }

        /* Actualizar cursos */
        System.out.print("Ingrese el Id del curso que quiere editar: ");
        int idEditar1 = scan.nextInt();
        
        scan.nextLine(); 

        System.out.print("Ingrese el nuevo titulo: ");
        String nuevoTitulo = scan.nextLine();

        System.out.print("Ingrese la nueva descripcion: ");
        String nuevaDescripcion = scan.nextLine();

        System.out.print("Ingrese el nuevo ID de Categoría: ");
        Integer fkIdCategoriaNuevo = scan.nextInt();



        Cursos cursosActualizados = cursosRepository.actualizarCursos(idEditar1, nuevoTitulo, nuevaDescripcion, fkIdCategoriaNuevo);

        if (cursosActualizados != null) {
            System.out.println(" curso actualizado:");
            System.out.println("Id: " + cursosActualizados.getIdCurso());
            System.out.println("Titulo: " + cursosActualizados.getTitulo());
            System.out.println("Descripcion: " + cursosActualizados.getDescripcion());
            System.out.println("Id Categoría: " + cursosActualizados.getFkidCategoria());
        } else {
            System.out.println("No se pudo actualizar la cursos :(");
        }

        /* Eliminar cursos */

        System.out.print("Ingrese el Id del curso que desea eliminar: ");
        int idEliminar1 = scan.nextInt();

        int filas1 = cursosRepository.eliminarCursos(idEliminar1);

        if (filas1 > 0) {
            System.out.println("curso eliminado correctamente.");
        } else {
            System.out.println("No se encontró el Curso o no se pudo eliminar :(");
        }


        scan.close();
    }

    
}