<template>
    <div style="width: 100vw; height: 100vh; overflow: hidden; display: flex">
        <div style="flex: 1">
            <el-image style="width: 100%; height: 100%" fit="cover"
                      src="https://image.itbaima.net/images/40/image-20230715099932425.jpeg"/>
        </div>
        <div class="welcome-title">
            <div style="font-size: 36px; font-weight: bold">欢迎来到我们的学习论坛~</div>
            <div style="margin-top: 12px; font-size: 17px; font-weight: bold">在这里，你可以学习Java、C/C++、Python等主流编程语言的开发技巧和最新技术</div>
            <div style="margin-top: 12px; font-size: 17px; font-weight: bold">并与志同道合的小伙伴一起学习，一起进步ヾ(^▽^*)</div>
        </div>
        <div style="width: 400px; background-color: white">
            <router-view/>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.welcome-title {
    position: absolute;
    bottom: 30px;
    left: 30px;
    color: white;
    text-shadow: 0 0 20px black;
}
</style>