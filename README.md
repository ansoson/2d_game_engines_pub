# TIC Handin README
#### Fill out this README before turning in this project. Make sure to fill this out again for each assignment!
---
### Banner ID: B01663531
---
### Already uploaded demo onto Slack:
---
## Primary Requirements:
| Requirement                                                                                                                    | Location in code or steps to view in game                                                           |
|--------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| Your handin must meet all global requirements                                                                                  | ```Should be fine```                                                                                |
| Your handin only crashes under exceptional circumstances (edge cases)                                                          | ```Hasnt crashed for me once```                                                                     |
| Your engine must contain a physics behavior that correctly holds and updates mass, force, impulse, velocity, and acceleration. | ```The collisionComponentPhysics and PhysicsComponent handle standard physics```                    |
| Your engine must handle static objects and restitution                                                                         | ```Physics objects have the ability to be considered movable and all contain a restitution value``` |
| Your game must contain a player-controlled unit above an immobile platform                                                     | ```I've got a little ninja guy that can be moved with WASD```                                       |
| The player controlled unit must fall when in the air                                                                           | ```the unit is affected by gravity```                                                               |
| The player controlled unit must not fall through the platform                                                                  | ```hasn't once for me```                                                                            |
| The player controlled unit must have a constant downward acceleration                                                          | ```Every tick gravity force is applied based on the number of nanoseconds that have passed```       |
| The player controlled unit must be able to jup, but only when standing on top of a platform                                    | ```W to jump, and only able to after settling on the ground```                                      |
| Your game must have three objects with visibly different restitution values that can collide with each other                   | ```The circle, square, and pentagon all have dfifferent restitution values and can collide```       |
| your game never crashes                                                                                                        | ```hasn't crashed on me once```                                                                     |


## Secondary Requirements:
| Requirement                                                                     | Location in code or steps to view in game                                                                                                                                                                           |
|---------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Your engine must meet all primary engine requirements                           | ```I think it does o3o```                                                                                                                                                                                           |
| Your engine must support collisions/MTVS with convex polygons                   | ```the debugger has been filled out and that logic imported into the game engine```                                                                                                                                 |
| The debugger must be extended to show collisions and MTV's with convex polygons | ```debugger is filled :D```                                                                                                                                                                                         |
| Your game must include a convex polygon                                         | ```The third object is a pentagon```                                                                                                                                                                                |

## Extras:
| Requirement | Location in code or steps to view in game  |
|---|---|
| [Copy and paste from handout] | ```File path, function name, or steps to replicate``` |

--------------------------------------------------------------

Instructions on how to run:
Go to the Main file in tnin and run from there

Known bugs: collisions are a little jittery when resting, and my current implementation of when a unit can jump requires a little too much time for 
it to feel nice i think.

Hours spent on assignment: 
Around 25
