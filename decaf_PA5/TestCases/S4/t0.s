          .text                         
          .globl main                   

          .data                         
          .align 2                      
_Main:                                  # virtual table
          .word 0                       # parent
          .word _STRING0                # class name



          .text                         
_Main_New:                              # function entry
          sw $fp, 0($sp)                
          sw $ra, -4($sp)               
          move $fp, $sp                 
          addiu $sp, $sp, -16           
_L15:                                   
          li    $t0, 4                  
          sw    $t0, 4($sp)             
          jal   _Alloc                  
          move  $t1, $v0                
          la    $t0, _Main              
          sw    $t0, 0($t1)             
          move  $v0, $t1                
          move  $sp, $fp                
          lw    $ra, -4($fp)            
          lw    $fp, 0($fp)             
          jr    $ra                     

main:                                   # function entry
          sw $fp, 0($sp)                
          sw $ra, -4($sp)               
          move $fp, $sp                 
          addiu $sp, $sp, -12           
_L16:                                   
          jal   _Main.f                 
          move  $sp, $fp                
          lw    $ra, -4($fp)            
          lw    $fp, 0($fp)             
          jr    $ra                     

_Main.f:                                # function entry
          sw $fp, 0($sp)                
          sw $ra, -4($sp)               
          move $fp, $sp                 
          addiu $sp, $sp, -24           
_L17:                                   
          li    $t0, 0                  
          li    $t0, 1                  
          li    $t0, 0                  
          move  $t1, $t0                
          li    $t0, 2                  
          move  $t2, $t0                
          li    $t0, 1                  
          add   $t0, $t2, $t0           
          sw    $t0, -8($fp)            
          sw    $t1, -12($fp)           
_L18:                                   
          lw    $t2, -12($fp)           
          lw    $t1, -8($fp)            
          sw    $t1, -8($fp)            
          sw    $t2, -12($fp)           
          beqz  $t2, _L24               
_L19:                                   
          lw    $t3, -12($fp)           
          lw    $t4, -8($fp)            
          li    $t0, 1                  
          sw    $t0, -16($fp)           
          sw    $t4, -8($fp)            
          sw    $t3, -12($fp)           
          beqz  $t3, _L21               
_L20:                                   
          lw    $t1, -12($fp)           
          lw    $t2, -8($fp)            
          lw    $t3, -16($fp)           
          sw    $t3, -16($fp)           
          sw    $t2, -8($fp)            
          sw    $t1, -12($fp)           
          jal   _Main.f                 
          lw    $t3, -16($fp)           
          lw    $t2, -8($fp)            
          lw    $t1, -12($fp)           
          sw    $t3, -16($fp)           
          sw    $t2, -8($fp)            
          sw    $t1, -12($fp)           
_L21:                                   
          lw    $t4, -12($fp)           
          lw    $t1, -8($fp)            
          lw    $t2, -16($fp)           
          li    $t0, 1                  
          add   $t0, $t1, $t0           
          move  $t1, $t0                
          sw    $t2, -16($fp)           
          sw    $t1, -8($fp)            
          sw    $t4, -12($fp)           
          beqz  $t4, _L23               
_L22:                                   
          lw    $t0, -12($fp)           
          lw    $t3, -8($fp)            
          lw    $t1, -16($fp)           
          li    $t2, 4                  
          sub   $t0, $t3, $t2           
          move  $t3, $t0                
          sw    $t1, -16($fp)           
          sw    $t3, -8($fp)            
_L23:                                   
          lw    $t2, -12($fp)           
          lw    $t0, -8($fp)            
          lw    $t3, -16($fp)           
          move  $t0, $t3                
          sw    $t2, -12($fp)           
          b     _L18                    
_L24:                                   
          move  $sp, $fp                
          lw    $ra, -4($fp)            
          lw    $fp, 0($fp)             
          jr    $ra                     




          .data                         
_STRING0:
          .asciiz "Main"                
