package core;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by joeba on 4/30/2017.
 */
public class ModelLoader {

    private class Vertex {public int v, vn, vt, o;}

    private Model model;
    private String root;
    private ArrayList<Vertex> vertList;
    private ArrayList<Vector3f> vertices, normals;
    private ArrayList<Vector2f> textures;
    private ArrayList<Integer> objects, material;

    private int currentGroup=-1;

    private ModelLoader(String file){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            root = file.substring(0, file.lastIndexOf('/'));;
            String line;

            vertices = new ArrayList<Vector3f>();
            textures = new ArrayList<Vector2f>();
            normals = new ArrayList<Vector3f>();
            objects = new ArrayList<Integer>();
            material = new ArrayList<Integer>();
            vertList = new ArrayList<Vertex>();

            while((line=br.readLine())!=null){
                if(line.startsWith("mtllib "))handleMtllib(line);
                else if(line.startsWith("o "))handleObject(line);
                else if(line.startsWith("v "))handleVertex(line);
                else if(line.startsWith("vn "))handleNormal(line);
                else if(line.startsWith("vt "))handleTexture(line);
                else if(line.startsWith("f "))handleIndices(line);
            }

            br.close();

            float[] vertices = new float[vertList.size() * 3];
            float[] textures = new float[vertList.size() * 2];
            float[] normals = new float[vertList.size() * 3];

            int vn=0, tn=0, nn=0;

            for(Vertex v:vertList){
                Vector3f verts = this.vertices.get(v.v);
                Vector2f texs = this.textures.get(v.vt);
                Vector3f norms = this.normals.get(v.vn);
                vertices[vn++]=verts.x;
                vertices[vn++]=verts.y;
                vertices[vn++]=verts.z;
                textures[tn++]=texs.x;
                textures[tn++]=texs.y;
                normals[nn++]=norms.x;
                normals[nn++]=norms.y;
                normals[nn++]=norms.z;
            }

            model = new Model(vertices, textures, normals);

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Model load(String file){
       ModelLoader ml = new ModelLoader(file);
       return ml.getModel();
    }

    private Model getModel(){
        return model;
    }

    private void handleIndices(String line) {
        String[] parts = line.split(" ");
        processVertex(parts[1]);
        processVertex(parts[2]);
        processVertex(parts[3]);
    }

    private void processVertex(String line){
        String[] parts = line.split("/");
        int vert = parseInt(parts[0]);
        int tex = parseInt(parts[1]);
        int norm = parseInt(parts[2]);
        Vertex v = new Vertex();
        v.v=vert-1;
        v.o=vert-1;
        v.vn=norm-1;
        v.vt=tex-1;
        vertList.add(v);
    }

    private void handleTexture(String line) {
        String[] parts = line.split(" ");
        textures.add(new Vector2f(parse(parts[1]),parse(parts[2])));

    }

    private void handleNormal(String line) {
        String[] parts = line.split(" ");
        normals.add(new Vector3f(parse(parts[1]),parse(parts[2]),parse(parts[3])));
    }

    private void handleVertex(String line) {
        String[] parts = line.split(" ");
        vertices.add(new Vector3f(parse(parts[1]),parse(parts[2]),parse(parts[3])));
        objects.add(currentGroup);
    }

    private void handleObject(String line) {
        currentGroup++;
    }

    private void handleMtllib(String line) {
        String[] parts = line.split(" ");
        String path = root +"/"+parts[1];
    }

    private float parse(String s){
        return Float.parseFloat(s);
    }

    private int parseInt(String s){
        return Integer.parseInt(s);
    }

}
