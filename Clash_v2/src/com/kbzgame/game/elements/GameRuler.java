/*
 * 游戏规则类考虑了物理事件和游戏事件的不统一
 * 如：物理世界的两个物体碰撞可能意味着游戏世界玩家和玩家，玩家和障碍物等的碰撞
 * 游戏规则是一类游戏元素和一类游戏元素的物理事件到游戏事件的转换
 * 在此，游戏元素类型，物理事件确定一种游戏事件
 */
package com.kbzgame.game.elements;

public abstract class GameRuler {
}
