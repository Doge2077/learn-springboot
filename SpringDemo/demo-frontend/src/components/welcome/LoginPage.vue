<template>
    <div style="text-align: center; margin: 0 20px">
        <div style="margin-top: 150px">
            <div style="font-size: 25px; font-weight: bold">登录</div>
            <div style="font-size: 14px; color: grey">进入系统前请先输入用户名和密码进行登录</div>
        </div>
        <div style="margin-top: 50px">
            <el-input type="text" placeholder="用户名/邮箱">
                <template #prefix>
                    <el-icon><User /></el-icon>
                </template>
            </el-input>
            <el-input type="password" style="margin-top: 10px" placeholder="密码">
                <template #prefix>
                    <el-icon><Lock /></el-icon>
                </template>
            </el-input>
        </div>
        <el-row style="margin-top: 5px">
            <el-col :span="12" style="text-align: left">
                <el-checkbox v-model="checked1" label="记住我"/>
            </el-col>
            <el-col :span="12" style="text-align: right; margin-top: 5px">
                <el-link>忘记密码</el-link>
            </el-col>
        </el-row>
        <div style="margin-top: 40px">
            <el-button style="width: 270px; box-shadow: 0 0 10px gainsboro" type="primary" plain round>立即登录</el-button>
        </div>
        <el-divider>
            <span style="color: grey; font-size: 12px">没有账号？注册一个！</span>
        </el-divider>
        <div>
            <el-button style="width: 270px; box-shadow: 0 0 10px gainsboro" type="success" plain round>立即注册</el-button>
        </div>
    </div>
</template>

<script setup>
import {User, Lock} from '@element-plus/icons-vue'
</script>

<style scoped>

</style>