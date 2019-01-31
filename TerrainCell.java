/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldsimulator;

/**
 *
 * @author joseph
 */
public class TerrainCell {
    private float altitude;//In meters
    private float rainfall;//In cm
    private float temperature;//In degrees C
    private Entity owner;
    
    public TerrainCell() {
        altitude = -1;
    }
    
    public TerrainCell(float a) {
        altitude = a;
    }
    
    public float getAltitude() {
        return altitude;
    }
    
    public void setAltitude(float a) {
        altitude = a;
    }
    
    
    public void safeSetAltitude(float a) {
        if (isGenerated()) return;
        altitude = a;
    }
    
    
    public float getRainfall() {
        return rainfall;
    }
    
    public float getTemperature() {
        return temperature;
    }
    
    public void setTemperature(float f) {
        temperature = f;
    }
    
    public void setRainfall(float r) {
        rainfall = r;
    }
    
    public void setOwner(Entity r) {
        owner = r;
    }
    
    public Entity getOwner() {
        return owner;
    }
    
    public boolean isGenerated() {
        if (altitude == -1) return false;
        return false;
    }
    
    /**
     * Sets the temperature
     * @param yCoord The y-coordinate of the cell from 0 - terrainCells[0].length
     * @param worldHeight terrainCells[0].length; the height of the world
     * @param maxTemp The max temperature at 0m at the equator
     */
    public void setTemperature(int yCoord, int worldHeight, float maxTemp) {
        float latPerYCoord = 180f / worldHeight;
        float latitude = (yCoord - worldHeight / 2) * latPerYCoord;
        temperature = tempFromLatitude(latitude, maxTemp);
        temperature -= 0.004 * altitude;
    }
    
    private float tempFromLatitude(float lat, float maxTemp) {
        return -0.00585f * (float) Math.pow(lat, 2) + maxTemp;
    }
}
