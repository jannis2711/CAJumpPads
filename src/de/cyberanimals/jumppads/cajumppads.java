package de.cyberanimals.jumppads;



import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class cajumppads
  extends JavaPlugin
  implements Listener
{
  public String pfx = "§bCAJumpPads §f|| §6";
  public String cpfx = "[blumenetwork]";
  public String epfx = "§c[blumenetwork] §9";
  public String msgperm = this.epfx + "Fehler. Du verfügst nicht über die nötigen Berechtigungen.";
  
  public void onEnable()
  {
    System.out.println(this.cpfx + " JumpPads start activating...");
    loadCAJumpPads();
    System.out.println(this.cpfx + " JumpPads loaded successfully.");
  }
  
  public void onDisable()
  {
    System.out.println(this.cpfx + " JumpPads start deactivating...");
    
    System.out.println(this.cpfx + " JumpPads deactivated successfully.");
  }
  
  public void loadCAJumpPads()
  {
    Bukkit.getPluginManager().registerEvents(this, this);
  }
  
  @EventHandler
  public void onSign(SignChangeEvent e)
  {
    Player p = e.getPlayer();
    if (e.getLine(0).equalsIgnoreCase("[JP]"))
    {
      if (!p.hasPermission("blumenetwork.jumppads.create"))
      {
        p.sendMessage(this.msgperm);
        e.getBlock().breakNaturally();
      }
      p.sendMessage(this.pfx + "JumpPad erstellt.");
    }
  }
  
  @EventHandler
  public void onWalkOverAJumpPad(PlayerMoveEvent e)
  {
    Location loc = e.getPlayer().getLocation();
    if (loc.getBlock().getType().equals(Material.IRON_PLATE))
    {
      loc.setY(loc.getY() - 1.0D);
      if (loc.getBlock().getType().equals(Material.SPONGE))
      {
        loc.setY(loc.getY() - 1.0D);
        if ((loc.getBlock().getState() instanceof Sign))
        {
          Sign s = (Sign)loc.getBlock().getState();
          if (s.getLine(0).equalsIgnoreCase("[JP]"))
          {
            String[] argumente = s.getLine(1).split(":");
            
            float x = Float.parseFloat(argumente[0]);
            float y = Float.parseFloat(argumente[1]);
            float z = Float.parseFloat(argumente[2]);
            
            Vector v = new Vector();
            v.setX(x);
            v.setY(y);
            v.setZ(z);
            
            e.getPlayer().setVelocity(v);
          }
        }
      }
      if (loc.getBlock().getType().equals(Material.GOLD_BLOCK))
      {
        loc.setY(loc.getY() - 1.0D);
        if ((loc.getBlock().getState() instanceof Sign))
        {
          Sign s = (Sign)loc.getBlock().getState();
          if (s.getLine(0).equalsIgnoreCase("[JP]"))
          {
            String[] argumente = s.getLine(1).split(":");
            
            float x = Float.parseFloat(argumente[0]);
            float y = Float.parseFloat(argumente[1]);
            float z = Float.parseFloat(argumente[2]);
            
            Vector v = new Vector();
            v.setX(x);
            v.setY(y);
            v.setZ(z);
            if (e.getPlayer().hasPermission("blumenetwork.premium")) {
              e.getPlayer().setVelocity(v);
            }
          }
        }
      }
    }
  }
}
