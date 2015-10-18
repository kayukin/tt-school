package net.thumbtack.lesson3;

import javafx.geometry.Point3D;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kayukin on 18.10.15.
 */
public class Rectangle3DTest {
    final double delta = 0.000001;

    @Test
    public void testGetZ() throws Exception {
        assertEquals(0, new Rectangle3D().getZ(), delta);
    }

    @Test
    public void testSetZ() throws Exception {
        Rectangle3D rect = new Rectangle3D();
        rect.setZ(9);
        assertEquals(9, rect.getZ(), delta);

    }

    @Test
    public void testGetLength() throws Exception {
        assertEquals(1, new Rectangle3D().getLength(), delta);
    }

    @Test
    public void testSetLength() throws Exception {
        Rectangle3D rect = new Rectangle3D();
        rect.setLength(9);
        assertEquals(9, rect.getLength(), delta);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("(0.0;0.0;0.0)", new Rectangle3D().toString());
    }

    @Test
    public void testMove() throws Exception {
        Rectangle3D rect = new Rectangle3D();
        rect.Move(1, 1, 1);
        assertTrue(rect.getX() == 1 && rect.getY() == 1 && rect.getZ() == 1);
    }

    @Test
    public void testArea() throws Exception {
        assertEquals(1, new Rectangle3D().Area(), delta);
    }

    @Test
    public void testVolume() throws Exception {
        assertEquals(1, new Rectangle3D().Volume(), delta);
    }

    @Test
    public void testIsPointIn() throws Exception {
        assertTrue(new Rectangle3D().IsPointIn(0.5, 0.5, 0.5));
    }

    @Test
    public void testIsPoint3DIn() throws Exception {
        assertFalse(new Rectangle3D().IsPointIn(new Point3D(0.5, 0.5, 1.5)));
    }

}