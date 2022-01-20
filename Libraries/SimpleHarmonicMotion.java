public final class SimpleHarmonicMotion
{
  public static double getDisplacement(
    double amplitude,
    double angular_frequency,
    double time_elapsed,
    double initial_phase_angle)
  {
    double output = angular_frequency * time_elapsed;
    
    output -= initial_phase_angle;
    
    output = Math.cos(output);
    
    output *= amplitude;
    
    return output;
  }
  
  public static double getVelocity(
    double amplitude,
    double angular_frequency,
    double time_elapsed,
    double initial_phase_angle)
  {
    double output = angular_frequency * time_elapsed;
    
    output -= initial_phase_angle;
    
    output = Math.sin(output);
    
    output *= (-1) * amplitude * angular_frequency;
    // speed is multiplied by -1 since it opposes the displacement 
    
    return output;
  }
  
  public static double getMaxVelocity(
    double amplitude,
    double angular_frequency)
  {
    return amplitude * angular_frequency; 
  } // returns speed at the equilibrium point 
  
  public static double getMaxAcceleration(
    double amplitude,
    double angular_frequency)
  {
    return amplitude * angular_frequency * angular_frequency;
  }
    
  public static double getAngularFrequency(
    double spring_constant,
    double mass_of_block)
  {
    double output = spring_constant / mass_of_block;
    
    output = Math.sqrt(output);
    
    return output;
  }
  
  public static double getTimePeriod(
    double spring_constant,
    double mass_of_block)
  {
    double output = mass_of_block / spring_constant;
    
    output = Math.sqrt(output);
    
    output *= 2 * Math.PI;
    
    return output;
  } // returns the length, in seconds, of 1 oscillation 
} 

/**
 * Library class for calculating position, velocity, and other mechanical quantities
 * relating to simple harmonic motion, such as that seen in pendulums.
**/
